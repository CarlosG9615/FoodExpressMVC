package es.daw.foodexpressmvc.service;


import es.daw.foodexpressmvc.dto.DishDTO;
import es.daw.foodexpressmvc.exception.ConnectionApiRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {

    private final WebClient webClientAPI;

    public List<DishDTO> getAllDishes(){
        DishDTO[] dishes;
        try{
            dishes = webClientAPI
                    .get()
                    .uri("/dishes")
                    .retrieve()
                    .bodyToMono(DishDTO[].class)
                    .block();
        }catch (Exception e){
            throw new ConnectionApiRestException("Could not connect to FoodExpress API");
        }
        return Arrays.asList(dishes);
    }
}
