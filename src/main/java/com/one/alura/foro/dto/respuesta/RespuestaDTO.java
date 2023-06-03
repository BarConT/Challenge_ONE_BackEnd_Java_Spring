package com.one.alura.foro.dto.respuesta;

import com.one.alura.foro.modelo.Respuesta;
import com.one.alura.foro.utils.DateTimeUtils;

import java.time.LocalDateTime;

public record RespuestaDTO(long id_respuesta, String usuario, String mensaje, LocalDateTime fechaCreacion, boolean solucion) {
    public RespuestaDTO(Respuesta respuesta) {
        this(
            respuesta.getId(),
            respuesta.getUsuario().getNombre(),
            respuesta.getMensaje(),
            respuesta.getFechaCreacion(),
            respuesta.getSolucion());
    }

    public String getFechaCreacion() {
        return DateTimeUtils.formatDateTime(fechaCreacion);
    }
}

