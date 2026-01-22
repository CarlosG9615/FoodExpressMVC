package es.daw.foodexpressmvc.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {

    private List<T> content;

    private int number;         // página actual (0-based)
    private int size;           // tamaño de página
    private long totalElements; // total de registros
    private int totalPages;     // total de páginas

    private boolean first;
    private boolean last;
}

