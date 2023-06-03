package com.one.alura.foro.controller;

import com.one.alura.foro.dto.usuario.DatosActualizarUsuario;
import com.one.alura.foro.dto.usuario.DatosListarUsuarios;
import com.one.alura.foro.dto.usuario.DatosRegistroUsuario;
import com.one.alura.foro.modelo.Respuesta;
import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.modelo.Usuario;
import com.one.alura.foro.repository.RespuestaRepository;
import com.one.alura.foro.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RespuestaRepository respuestaRepository;
    @GetMapping
    public List<DatosListarUsuarios> listarUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(DatosListarUsuarios::new)
                .toList();
    }

    @PostMapping
    public void registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        usuarioRepository.save(new Usuario(datosRegistroUsuario));
    }

    @PutMapping
    @Transactional
    public void modificarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizar(datosActualizarUsuario);
    }

    @DeleteMapping ("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }


    @PostMapping("/{respuestaId}")
    @Transactional
    public void actualizarEstatusRespuesta(@PathVariable("respuestaId") Long respuestaId) {
        Respuesta respuesta = respuestaRepository.getReferenceById(respuestaId);
        Topico topico = respuesta.getTopico();
        Usuario usuario = usuarioRepository.getReferenceById(respuesta.getId());
        usuario.actualizarSolucion(respuesta, topico);
    }
}
