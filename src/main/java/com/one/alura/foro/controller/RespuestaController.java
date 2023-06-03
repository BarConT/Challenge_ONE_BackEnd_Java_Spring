package com.one.alura.foro.controller;

import com.one.alura.foro.dto.respuesta.DatosActualizarRespuesta;
import com.one.alura.foro.dto.respuesta.DatosListarRespuestas;
import com.one.alura.foro.dto.respuesta.DatosRegistroRespuesta;
import com.one.alura.foro.dto.respuesta.RespuestaDTO;
import com.one.alura.foro.modelo.Respuesta;
import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.modelo.Usuario;
import com.one.alura.foro.repository.RespuestaRepository;
import com.one.alura.foro.repository.TopicoRepository;
import com.one.alura.foro.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TopicoRepository topicoRepository;
    @GetMapping
    public List<DatosListarRespuestas> listarRespuestas() {
        return respuestaRepository.findAll().stream().map(DatosListarRespuestas::new).toList();
    }

    @GetMapping ("/{id}")
    public List<RespuestaDTO> listarRespuestaPorTopico(@PathVariable Long id) {
        return respuestaRepository.findByTopicoId(id).stream().map(RespuestaDTO::new).toList();
    }

    @PostMapping
    public void registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(datosRegistroRespuesta.id_usuario());
        Optional<Topico> opcionalTopico = topicoRepository.findById(datosRegistroRespuesta.id_topico());
        if (optionalUsuario.isPresent() && opcionalTopico.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Topico topico = opcionalTopico.get();
            respuestaRepository.save(new Respuesta(datosRegistroRespuesta, usuario, topico));
        } else {
            throw new IllegalArgumentException("El usuario con ID " + datosRegistroRespuesta.id_usuario() + " no existe");
        }
    }

    @PutMapping
    @Transactional
    public void actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.acualizar(datosActualizarRespuesta);
    }

    @DeleteMapping ("/{id}")
    public void eliminarRespuesta(@PathVariable long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
    }

}
