package com.one.alura.foro.dto.topico;

import com.one.alura.foro.modelo.Curso;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje
) {
}
