package es.daw.foodexpressmvc.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DishDTO {
    private String name;
    private BigDecimal price;
    private String category;
    private String restaurantName;
}
