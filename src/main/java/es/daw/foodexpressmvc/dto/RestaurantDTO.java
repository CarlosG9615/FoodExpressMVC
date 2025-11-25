package es.daw.foodexpressmvc.dto;

import lombok.Data;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
}