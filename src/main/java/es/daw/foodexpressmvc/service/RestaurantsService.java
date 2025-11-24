package es.daw.foodexpressmvc.service;

import es.daw.foodexpressmvc.dto.RestaurantDTO;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantsService {

    private final WebClient webClientAPI;

    public List<RestaurantDTO> getAllRestaurants(){

        RestaurantDTO[] restaurants;

        try {
            restaurants = webClientAPI
                    .get()
                    .uri("/restaurants")
                    .retrieve()
                    .bodyToMono(RestaurantDTO[].class)
                    .block(); //asíncrono
        }catch (Exception e){
            // Pendiente crear excepción propia
            // Pendiente crear Globla ExceptionHancler: que lea la exceión y redirija a api-error
            //
            throw new ConnectionApiRestException("Could not connect to FoodExpress API");
        }

        return Arrays.asList(restaurants);


    }

    public RestaurantDTO create(RestaurantDTO dto){

        String token = ""; //lo cogemos de un servicio de autenticación!!!! el servicio da el token

        RestaurantDTO restaurant;

        try {
            restaurant = webClientAPI
                    .post()
                    .uri("/restaurants")
                    //.header() ... meter el token
                    .bodyValue(dto)
                    .retrieve()
                    .bodyToMono(RestaurantDTO.class)
                    .block(); //asíncrono
        }catch (Exception e){
            // Pendiente crear excepción propia
            // Pendiente crear Globla ExceptionHancler: que lea la exceión y redirija a api-error
            //
            throw new ConnectionApiRestException("Could not connect to FoodExpress API to create restaurant");
        }

        return restaurant;

    }

}
