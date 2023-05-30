package com.one.alura.foro.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.one.alura.foro.dto.DatosActualizarRespuesta;
import com.one.alura.foro.dto.DatosRegistroRespuesta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private Boolean solucion = false;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Usuario usuario, Topico topico) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.usuario = usuario;
        this.topico = topico;
    }

    public void acualizar(DatosActualizarRespuesta datosActualizarRespuesta) {
        if(datosActualizarRespuesta.mensaje()!=null) {
            this.mensaje = datosActualizarRespuesta.mensaje();
        }
    }
}

