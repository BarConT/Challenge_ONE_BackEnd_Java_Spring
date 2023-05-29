package com.one.alura.foro.controller;

import com.one.alura.foro.dto.DatosActualizarTopico;
import com.one.alura.foro.dto.DatosRegistroTopico;
import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        topicoRepository.save(new Topico(datosRegistroTopico));
    }
    @GetMapping
    public Page<Topico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion);
    }

    @GetMapping ("/{id}")
    public Topico listarTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        return topico;
    }

    @PutMapping
    @Transactional
    public void actualizarTopico(@RequestBody DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizar(datosActualizarTopico);
    }

    @DeleteMapping ("/{id}")
    public void eliminarTopico(@PathVariable long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
    }
}
