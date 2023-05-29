package com.one.alura.foro.modelo;

import com.one.alura.foro.dto.DatosActualizarTopico;
import com.one.alura.foro.dto.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private StatusTopico estatus = StatusTopico.NO_RESPONDIDO;
    private String autor;
    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(DatosRegistroTopico datosTopico) {
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.autor = datosTopico.autor();
        this.curso = datosTopico.curso();
    }

    public void actualizar(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo()!=null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje()!=null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.curso()!=null) {
            this.curso = datosActualizarTopico.curso();
        }
        if (datosActualizarTopico.estatus()!=null) {
            this.estatus = datosActualizarTopico.estatus();
        }

    }
}
