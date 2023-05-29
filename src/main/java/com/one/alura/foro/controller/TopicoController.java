package com.one.alura.foro.controller;

import com.one.alura.foro.dto.DatosRegistroTopico;
import com.one.alura.foro.modelo.Topico;
import com.one.alura.foro.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }
}
