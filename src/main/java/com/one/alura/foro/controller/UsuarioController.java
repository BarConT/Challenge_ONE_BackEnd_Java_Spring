package com.one.alura.foro.controller;

import com.one.alura.foro.domain.dto.usuario.DatosActualizarUsuario;
import com.one.alura.foro.domain.dto.usuario.DatosListarUsuarios;
import com.one.alura.foro.domain.dto.usuario.DatosRegistroUsuario;
import com.one.alura.foro.domain.dto.usuario.DatosRespuestaUsuario;
import com.one.alura.foro.domain.modelo.Respuesta;
import com.one.alura.foro.domain.modelo.Topico;
import com.one.alura.foro.domain.modelo.Usuario;
import com.one.alura.foro.domain.repository.RespuestaRepository;
import com.one.alura.foro.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<DatosListarUsuarios>> listarUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll().stream().map(DatosListarUsuarios::new).toList());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> listarUsuarioPorId(@PathVariable("id") long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> modificarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizar(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
