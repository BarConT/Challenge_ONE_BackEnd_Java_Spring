package com.one.alura.foro.dto;

import com.one.alura.foro.modelo.Curso;
import com.one.alura.foro.modelo.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        Curso curso,
        StatusTopico estatus
) {
}
