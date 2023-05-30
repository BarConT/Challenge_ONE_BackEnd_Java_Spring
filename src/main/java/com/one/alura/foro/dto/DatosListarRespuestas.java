package com.one.alura.foro.dto;

import com.one.alura.foro.modelo.Respuesta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DatosListarRespuestas(long id, String id_usuario, String mensaje, LocalDateTime fechaCreacion, boolean solucion) {
    public DatosListarRespuestas(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getUsuario().getNombre(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getSolucion());
    }
    public String getFechaCreacion() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fechaCreacion.format(formato);
    }
}
