package com.one.alura.foro.dto.topico;

import com.one.alura.foro.dto.respuesta.RespuestaDTO;
import com.one.alura.foro.modelo.StatusTopico;
import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TopicoDTO(Long id_topico, String usuario, String titulo, String mensaje, LocalDateTime fechaCreacion, StatusTopico estatus, String curso, List<RespuestaDTO> listaRespuestas) {
    public TopicoDTO(Topico topico) {
        this(
            topico.getId(),
            topico.getUsuario().getNombre(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getEstatus(),
            topico.getCurso().toString(),
            topico.getRespuestas()
                    .stream()
                    .map(RespuestaDTO::new)
                    .collect(Collectors.toList())
        );
    }
    public String getFechaCreacion() {
        return DateTimeUtils.formatDateTime(fechaCreacion);
    }
}
