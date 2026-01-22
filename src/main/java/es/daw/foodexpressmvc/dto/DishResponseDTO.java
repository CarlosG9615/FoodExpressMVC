package es.daw.foodexpressmvc.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DishResponseDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private String category;

    @Valid
    private RestaurantDTO restaurant;
}
