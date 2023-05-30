package com.one.alura.foro.dto;

import com.one.alura.foro.modelo.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
//        @NotBlank
//        String autor,
        @NotNull
        Long usuario_id,
        @NotNull
        Curso curso
        ) {

}
