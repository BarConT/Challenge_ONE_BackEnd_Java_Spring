package com.one.alura.foro.dto;

import com.one.alura.foro.modelo.Curso;
import com.one.alura.foro.modelo.Respuesta;
import com.one.alura.foro.modelo.StatusTopico;
import com.one.alura.foro.modelo.Topico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record DatosListarTopicos(Long id, Curso curso, String titulo, StatusTopico estatus, LocalDateTime fechaCreacion, String usuario, List<Respuesta> listaRespuestas) {
    public DatosListarTopicos(Topico topico) {
        this(topico.getId(), topico.getCurso(), topico.getTitulo(), topico.getEstatus(),topico.getFechaCreacion(), topico.getUsuario().getNombre(), topico.getRespuestas());
    }

    public String getFechaCreacion() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fechaCreacion.format(formato);
    }
}
