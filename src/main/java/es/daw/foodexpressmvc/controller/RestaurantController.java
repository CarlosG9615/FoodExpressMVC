package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.RestaurantDTO;
import es.daw.foodexpressmvc.service.RestaurantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantsService  restaurantsService;

    @GetMapping("/restaurants")
    public String listRestaurants(Model model) {

        List<RestaurantDTO> restaurants = restaurantsService.getAllRestaurants();

        model.addAttribute("restaurants", restaurants);

        return "restaurants";

    }

}
