package es.daw.foodexpressmvc.service;


import es.daw.foodexpressmvc.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantsService {

    private final WebClient webClientAPI;

    public List<RestaurantDTO> getAllRestaurants(){

        RestaurantDTO[] restaurants;

        try{
            restaurants = webClientAPI.get()
                    .uri("/restaurants")
                    .retrieve()
                    .bodyToMono(RestaurantDTO[].class)
                    .block();
        }catch(Exception e){
            // Pendiente crear excepcion propia
            // Pendiente crear el global exceptionHandler : que lea la excepcion y redirija a api-error
            throw new ConnectionApiRestException("Could not connect to FoodExpress API");
        }

        return List.of(restaurants);
    }

} 
