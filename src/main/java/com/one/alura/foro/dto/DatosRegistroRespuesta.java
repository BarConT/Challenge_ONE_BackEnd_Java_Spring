package com.one.alura.foro.dto;

import com.one.alura.foro.modelo.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        Long id_usuario,
        @NotNull
        Long id_topico
        ) {
}
