package com.one.alura.foro.controller;

import com.one.alura.foro.dto.DatosActualizarTopico;
import com.one.alura.foro.dto.DatosListarTopicos;
import com.one.alura.foro.dto.DatosRegistroTopico;
import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.modelo.Usuario;
import com.one.alura.foro.repository.TopicoRepository;
import com.one.alura.foro.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping ("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(datosRegistroTopico.usuario_id());
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            topicoRepository.save(new Topico(datosRegistroTopico, usuario));
        } else {
            throw new IllegalArgumentException("El usuario con ID " + datosRegistroTopico.usuario_id() + " no existe");
        }

    }
    @GetMapping
    public Page<DatosListarTopicos> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListarTopicos::new);
    }

    @GetMapping ("/{id}")
    public Topico listarTopicoPorId(@PathVariable Long id) {
        return topicoRepository.findById(id).orElse(null);
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
