package com.one.alura.foro.controller;

import com.one.alura.foro.dto.topico.DatosActualizarTopico;
import com.one.alura.foro.dto.topico.DatosListarTopicos;
import com.one.alura.foro.dto.topico.DatosRegistroTopico;
import com.one.alura.foro.dto.topico.TopicoDTO;
import com.one.alura.foro.modelo.Curso;
import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.modelo.Usuario;
import com.one.alura.foro.repository.CursoRepository;
import com.one.alura.foro.repository.TopicoRepository;
import com.one.alura.foro.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(datosRegistroTopico.usuario_id());
        Optional<Curso> optionalCurso = cursoRepository.findById(datosRegistroTopico.curso_id());
        if (optionalUsuario.isPresent() && optionalCurso.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Curso curso = optionalCurso.get();
            topicoRepository.save(new Topico(datosRegistroTopico, usuario, curso));
        } else {
            throw new IllegalArgumentException("El usuario con ID " + datosRegistroTopico.usuario_id() + " o el curso con ID " + datosRegistroTopico.curso_id() + " no existen");
        }

    }
    @GetMapping
    public Page<DatosListarTopicos> listarTopicos(
        @PageableDefault(page = 0, size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC)
        Pageable paginacion) {
            return topicoRepository.findAll(paginacion).map(DatosListarTopicos::new);
    }

    @GetMapping ("/{id}")
    public TopicoDTO listarTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        return new TopicoDTO(topico);
    }

    @PutMapping
    @Transactional
    public void actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizar(datosActualizarTopico);
    }

    @DeleteMapping ("/{id}")
    public void eliminarTopico(@PathVariable long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
    }

}
