package es.daw.foodexpressapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantDTO {
    private String name;
    private String address;
    private String phone;

}
