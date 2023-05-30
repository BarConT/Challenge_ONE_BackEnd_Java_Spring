package com.one.alura.foro.controller;

import com.one.alura.foro.dto.DatosActualizarUsuario;
import com.one.alura.foro.dto.DatosListarUsuario;
import com.one.alura.foro.dto.DatosRegistroUsuario;
import com.one.alura.foro.modelo.Usuario;
import com.one.alura.foro.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @GetMapping
    public List<DatosListarUsuario> listarUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(DatosListarUsuario::new)
                .toList();
    }

    @PostMapping
    public void registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        usuarioRepository.save(new Usuario(datosRegistroUsuario));
    }

    @PutMapping
    @Transactional
    public void modificarUsuario(@RequestBody DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizar(datosActualizarUsuario);
    }

    @DeleteMapping ("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }
}
