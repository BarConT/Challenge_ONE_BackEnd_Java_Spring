package com.one.alura.foro.dto;

import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.modelo.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public record DatosListarUsuario(Long id, String nombre, String email, List<String> listaTopicos) {
    public DatosListarUsuario(Usuario usuario) {
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
