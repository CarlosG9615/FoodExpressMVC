package es.daw.foodexpressmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    // Normalmente el ID solo lo envía la API, no el cliente
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    // ===== NAME =====
    @NotBlank(message = "Restaurant name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    // Permite letras, números, espacios y caracteres típicos ("La Pizzería 2", "Sushi-Bar")
    @Pattern(
            regexp = "^[A-Za-z0-9ÁÉÍÓÚáéíóúñÑüÜ '-]{2,100}$",
            message = "Name contains invalid characters"
    )
    private String name;

    // ===== ADDRESS =====
    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 150, message = "Address must be between 5 and 150 characters")
    // Dirección realista: letras, números, puntos, comas, º, ª...
    @Pattern(
            regexp = "^[A-Za-z0-9ÁÉÍÓÚáéíóúñÑüÜ ,./ºª-]{5,150}$",
            message = "Address contains invalid characters"
    )
    private String address;

    // ===== PHONE =====
    @NotBlank(message = "Phone is required")
    @Size(min = 9, max = 20, message = "Phone must be between 9 and 20 digits")
    // Admite: números, espacios, guiones, + (por si es internacional)
//    @Pattern(
//            regexp = "^[0-9 +()-]{9,20}$",
//            message = "Invalid phone format"
//    )
    private String phone;

}

