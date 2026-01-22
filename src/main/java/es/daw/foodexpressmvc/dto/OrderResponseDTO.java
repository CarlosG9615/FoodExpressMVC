package es.daw.foodexpressmvc.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderResponseDTO {
    Long orderId;
    String username;
    String restaurantName;
    Long totalItems;
    BigDecimal totalAmount;
}
