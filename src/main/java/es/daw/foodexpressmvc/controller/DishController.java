package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.DishDTO;
import es.daw.foodexpressmvc.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/dishes")
    public String listDishes(Model model, Authentication authentication) {

        List<DishDTO> dishes = dishService.getAllDishes();

        model.addAttribute("dishes", dishes);
        model.addAttribute("username", authentication.getName());

        return "dishes/dishes";

    }
}
