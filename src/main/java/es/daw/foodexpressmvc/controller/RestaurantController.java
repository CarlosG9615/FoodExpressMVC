package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.RestaurantDTO;
import es.daw.foodexpressmvc.service.RestaurantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantsService  restaurantsService;

    @GetMapping
    public String listRestaurants(Model model) {

        List<RestaurantDTO> restaurants = restaurantsService.getAllRestaurants();

        model.addAttribute("restaurants", restaurants);

        return "restaurants/restaurants";

    }

    @GetMapping("/menu")
    public String showMenu(Principal principal, Model model) {
        model.addAttribute(principal.getName());
        return "restaurants/restaurants-menu";

    }

    @GetMapping("/create")
    public String showForm(Model model, Principal principal) {
        model.addAttribute(principal.getName());
        model.addAttribute("restaurant", new RestaurantDTO());
        return "restaurants/restaurant-create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("restaurant") RestaurantDTO restaurantDTO, Model model, Principal principal) {

        RestaurantDTO saved = restaurantsService.create(restaurantDTO);
        model.addAttribute(saved);
        // Pendiente: enviar el restaurante salvado..
        return "restaurants/create-success";
    }


}
