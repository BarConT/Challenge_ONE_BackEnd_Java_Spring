package com.one.alura.foro.dto;

import com.one.alura.foro.modelo.Curso;
import com.one.alura.foro.modelo.StatusTopico;
import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.modelo.Usuario;

import java.time.LocalDateTime;

public record DatosListarTopicos(Long id, Curso curso, String titulo, StatusTopico estatus, LocalDateTime fechaCreacion, String usuario) {
    public DatosListarTopicos(Topico topico) {
        this(topico.getId(), topico.getCurso(), topico.getTitulo(), topico.getEstatus(),topico.getFechaCreacion(), topico.getUsuario().getNombre());
    }
}
