package com.one.alura.foro.dto.usuario;

import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.modelo.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public record DatosListarUsuarios(long id_usuario, String nombre, String email, List<String> listaTopicos) {
    public DatosListarUsuarios(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTopicos()
                        .stream()
                        .map(Topico::getTitulo)
                        .collect(Collectors.toList())
        );
    }
}
