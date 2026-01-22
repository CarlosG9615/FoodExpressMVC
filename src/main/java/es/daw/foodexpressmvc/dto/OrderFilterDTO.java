package es. daw.foodexpressmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilterDTO {
    private String status;
    private Long userId;
    private Long restaurantId;
}