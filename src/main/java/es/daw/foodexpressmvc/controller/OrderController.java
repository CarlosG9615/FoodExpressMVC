package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.OrderFilterDTO;
import es.daw.foodexpressmvc.dto.OrderResponseDTO;
import es.daw.foodexpressmvc.dto.PageResponse;
import es.daw.foodexpressmvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("orders")
    public String listOrders(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size,
                             @RequestParam(defaultValue = "id") String sort,
                             @RequestParam(defaultValue = "asc") String dir,
                             Model model){
        PageResponse<OrderResponseDTO> orderPage= orderService.getAllOrders(page, size, sort, dir);
        //objeto filter para el formulario
        OrderFilterDTO filter = new OrderFilterDTO();
        model.addAttribute("page", orderPage);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        model.addAttribute("size", size);
        model.addAttribute("filter", filter);
        return "orders/orders-list";
    }
    // Buscar órdenes con filtros
    @GetMapping("orders/search")
    public String searchOrders(
            @ModelAttribute OrderFilterDTO filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String dir,
            Model model
    ) {
        try {
            PageResponse<OrderResponseDTO> orderPage = orderService. searchOrders(
                    filter.getStatus(),
                    filter.getUserId(),
                    filter.getRestaurantId(),
                    page,
                    size,
                    sort,
                    dir
            );

            model.addAttribute("page", orderPage);
            model. addAttribute("sort", sort);
            model.addAttribute("dir", dir);
            model.addAttribute("size", size);
            model.addAttribute("filter", filter);  // Mantener los filtros en el formulario

            return "orders/orders-list";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("filter", filter);
            return "orders/orders-list";
        }
    }


}

