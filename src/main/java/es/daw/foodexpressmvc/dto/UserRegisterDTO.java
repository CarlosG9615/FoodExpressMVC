package es.daw.foodexpressmvc.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank(message = "Username no puede estar en blanco")
    @Size(min = 4, message = "Username tiene que tener al menos 4 characters")
    private String username;

    @Size(max = 100, message = "Nombre completo no puede superar 100 caracteres")
    private String fullName;

    @NotBlank(message = "Email no puede estar en blanco")
    private String email;

    @NotBlank(message = "Password no puede estar en blanco")
    private String password;

    @NotBlank(message = "Password no puede estar en blanco")
    private String confirmPassword;
}

