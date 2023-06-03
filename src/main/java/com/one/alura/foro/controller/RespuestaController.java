package com.one.alura.foro.controller;

import com.one.alura.foro.domain.respuesta.*;
import com.one.alura.foro.domain.modelo.Respuesta;
import com.one.alura.foro.domain.modelo.Topico;
import com.one.alura.foro.domain.modelo.Usuario;
import com.one.alura.foro.domain.repository.RespuestaRepository;
import com.one.alura.foro.domain.repository.TopicoRepository;
import com.one.alura.foro.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(datosRegistroRespuesta.id_usuario());
        Optional<Topico> opcionalTopico = topicoRepository.findById(datosRegistroRespuesta.id_topico());
        if (optionalUsuario.isPresent() && opcionalTopico.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Topico topico = opcionalTopico.get();
            Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta, usuario, topico));
            URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
            return ResponseEntity.created(url).body(new DatosRespuestaRespuesta(respuesta));
        } else {
            throw new IllegalArgumentException("El usuario con ID " + datosRegistroRespuesta.id_usuario() + " no existe");
        }
    }

    @GetMapping
    public ResponseEntity<List<DatosListarRespuestas>> listarRespuestas() {
        return ResponseEntity.ok(respuestaRepository.findAll().stream().map(DatosListarRespuestas::new).toList());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DatosRespuestaRespuesta> listarRespuestaPorId(@PathVariable("id") long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaRespuesta(respuesta));
    }

    @GetMapping ("/topico/{idTopico}")
    public ResponseEntity<List<RespuestaDTO>> listarRespuestaPorTopico(@PathVariable("idTopico") Long id) {
        return ResponseEntity.ok(respuestaRepository.findByTopicoId(id).stream().map(RespuestaDTO::new).toList());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaDTO> actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.acualizar(datosActualizarRespuesta);
        return ResponseEntity.ok(new RespuestaDTO(respuesta));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable("id") long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return ResponseEntity.noContent().build();
    }

}
