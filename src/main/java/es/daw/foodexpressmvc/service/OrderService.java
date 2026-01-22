package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.ErrorDTO;
import es.daw.foodexpressmvc.dto.OrderResponseDTO;
import es.daw.foodexpressmvc.dto.PageResponse;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final WebClient webClientAPI;
    public PageResponse<OrderResponseDTO> getAllOrders(int page, int size, String sort, String dir){
        return webClientAPI
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/orders/paginated")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .queryParam("sort", sort + "," + dir)  // ← "id,desc"
                        .build()
                )
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(ErrorDTO.class)
                                .defaultIfEmpty(new ErrorDTO())
                                .flatMap(errorDto -> Mono.error(new ConnectionApiRestException(
                                        buildApiErrorMessage(errorDto.getPath(), errorDto.getStatus(), errorDto)
                                )))
                )
                .bodyToMono(new ParameterizedTypeReference<PageResponse<OrderResponseDTO>>() {})
                .onErrorMap(ex ->{
                    if(ex instanceof ConnectionApiRestException) return ex;
                    return new ConnectionApiRestException("No se pudo conectar con el API (GET /orders). Detalle: " + ex.getMessage());
                })
                .block();

    }
    // NUEVO: Método con filtros
    public PageResponse<OrderResponseDTO> searchOrders(String status, Long userId, Long restaurantId,
                                                       int page, int size, String sort, String dir) {
        return webClientAPI
                .get()
                .uri(uriBuilder -> {
                    var builder = uriBuilder
                            .path("/orders/paginated")
                            .queryParam("page", page)
                            .queryParam("size", size)
                            .queryParam("sort", sort + "," + dir);

                    // Añadir filtros solo si tienen valor
                    if (status != null && !status.isBlank()) {
                        builder. queryParam("status", status);
                    }
                    if (userId != null) {
                        builder.queryParam("userId", userId);
                    }
                    if (restaurantId != null) {
                        builder.queryParam("restaurantId", restaurantId);
                    }

                    return builder.build();
                })
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> clientResponse. bodyToMono(ErrorDTO. class)
                                .defaultIfEmpty(new ErrorDTO())
                                .flatMap(errorDto -> Mono.error(new ConnectionApiRestException(
                                        buildApiErrorMessage(errorDto.getPath(), errorDto.getStatus(), errorDto)
                                )))
                )
                .bodyToMono(new ParameterizedTypeReference<PageResponse<OrderResponseDTO>>() {})
                .onErrorMap(ex -> {
                    if (ex instanceof ConnectionApiRestException) return ex;
                    return new ConnectionApiRestException("No se pudo conectar con el API (GET /orders/search). Detalle: " + ex.getMessage());
                })
                .block();
    }

    private String buildApiErrorMessage(String operation, int httpStatus, ErrorDTO errorDto) {

        String apiMessage = (errorDto.getMessage() != null && !errorDto.getMessage().isBlank())
                ? errorDto.getMessage()
                : "sin detalle";

        String apiError = (errorDto.getError() != null && !errorDto.getError().isBlank())
                ? errorDto.getError()
                : "HTTP error";

        String apiPath = (errorDto.getPath() != null && !errorDto.getPath().isBlank())
                ? errorDto.getPath()
                : "(path no informado)";

        return """
           %s falló.
           HTTP %d - %s
           Message: %s
           Path: %s
           """.formatted(operation, httpStatus, apiError, apiMessage, apiPath).trim();
    }
}
