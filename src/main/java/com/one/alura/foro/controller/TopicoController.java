package com.one.alura.foro.controller;

import com.one.alura.foro.domain.dto.topico.*;
import com.one.alura.foro.domain.modelo.Curso;
import com.one.alura.foro.domain.modelo.Topico;
import com.one.alura.foro.domain.modelo.Usuario;
import com.one.alura.foro.domain.repository.CursoRepository;
import com.one.alura.foro.domain.repository.TopicoRepository;
import com.one.alura.foro.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping ("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(datosRegistroTopico.usuario_id());
        Optional<Curso> optionalCurso = cursoRepository.findById(datosRegistroTopico.curso_id());
        if (optionalUsuario.isPresent() && optionalCurso.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Curso curso = optionalCurso.get();
            Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, usuario, curso));
            URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(new DatosRespuestaTopico(topico));
        } else {
            throw new IllegalArgumentException("El usuario con ID " + datosRegistroTopico.usuario_id() + " o el curso con ID " + datosRegistroTopico.curso_id() + " no existen");
        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarTopicos>> listarTopicos(
        @PageableDefault(page = 0, size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC)
        Pageable paginacion) {
            return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListarTopicos::new));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<TopicoDTO> listarTopicoPorId(@PathVariable("id") Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(new TopicoDTO(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizar(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable("id") long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}
