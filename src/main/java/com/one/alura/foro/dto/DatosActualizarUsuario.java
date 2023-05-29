package com.one.alura.foro.dto;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull
        Long id,
        String nombre,
        String contrasenia
) {
}
