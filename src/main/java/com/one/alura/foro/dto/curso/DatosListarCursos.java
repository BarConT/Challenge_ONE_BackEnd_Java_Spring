package com.one.alura.foro.dto.curso;

import com.one.alura.foro.modelo.Curso;

public record DatosListarCursos(long id_curso, String nombre, String categoria) {
    public DatosListarCursos(Curso curso) {
        this(
            curso.getId(),
            curso.getNombre(),
            curso.getCategoria()
        );
    }
}
