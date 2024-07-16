package com.aluraforohub.forohub.persistence.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String fechaCreacion,
        @NotBlank
        String status,
        @NotNull
        Integer autor,
        @NotNull
        Integer curso,
        @NotNull
        Integer respuestas

) {
}
