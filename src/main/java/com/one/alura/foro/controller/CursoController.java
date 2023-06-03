package com.one.alura.foro.controller;

import com.one.alura.foro.dto.curso.DatosActualizarCurso;
import com.one.alura.foro.dto.curso.DatosListarCursos;
import com.one.alura.foro.dto.curso.DatosRegistroCurso;
import com.one.alura.foro.modelo.Curso;
import com.one.alura.foro.repository.CursoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;
    @PostMapping
    public void registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso) {
        cursoRepository.save(new Curso(datosRegistroCurso));
    }

    @GetMapping
    public List<DatosListarCursos> listadoCursos() {
        return  cursoRepository.findAll().stream().map(DatosListarCursos::new).toList();
    }

    @PutMapping
    @Transactional
    public void modificarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizar(datosActualizarCurso);
    }

    @DeleteMapping ("/{id}")
    public void eliminarCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
    }
}
