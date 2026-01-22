package es.daw.foodexpressmvc.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {

    private LocalDateTime timestamp; // Momento del error
    private int status;              // Código HTTP (404, 400, 500...)
    private String error;            // Nombre del error: "Not Found", "Bad Request"...
    private String message;          // Mensaje detallado
    private String path;             // Endpoint que falló (/api/dishes, etc.)
}
