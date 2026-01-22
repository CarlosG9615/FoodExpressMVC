package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.ApiLoginRequest;
import es.daw.foodexpressmvc.dto.ApiLoginResponse;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import es.daw.foodexpressmvc.session.ApiSessionToken;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/*
Spring Boot MVC es un entorno servlet, y Spring inyecta automáticamente:
HttpSession
HttpServletRequest
HttpServletResponse
Principal
 */
@Service
@RequiredArgsConstructor
public class ApiAuthService {

    private final ApiSessionToken apiSessionToken;

    private final WebClient webClientAuth;

    @Value("${api.auth-username}")
    private String apiUsername;

    @Value("${api.auth-password}")
    private String apiPassword;

    public String getToken() {

        if (apiSessionToken.getToken() != null) {
            return apiSessionToken.getToken();
        }

        ApiLoginRequest request = new ApiLoginRequest();
        request.setUsername(apiUsername);
        request.setPassword(apiPassword);

        try {
            ApiLoginResponse response = webClientAuth
                    .post()
                    //.uri("") // authURL ya es /auth/login
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(ApiLoginResponse.class)
                    .block();

            if (response == null || response.getToken() == null) {
                throw new ConnectionApiRestException("API login failed: no token received");
            }

            apiSessionToken.setToken(response.getToken());

            return response.getToken();
        } catch (Exception ex) {
            throw new ConnectionApiRestException(ex.getMessage());
        }
    }
}
