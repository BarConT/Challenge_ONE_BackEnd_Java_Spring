package com.one.alura.foro.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.one.alura.foro.dto.usuario.DatosActualizarUsuario;
import com.one.alura.foro.dto.usuario.DatosRegistroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "usuarios")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasenia;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Topico> topicos;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private List<Respuesta> respuestas;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.email = datosRegistroUsuario.email();
        this.contrasenia = datosRegistroUsuario.contrasenia();
    }

    public void actualizar(DatosActualizarUsuario datosActualizarUsuario) {
        if (datosActualizarUsuario.nombre()!=null) {
            this.nombre = datosActualizarUsuario.nombre();
        }
        if (datosActualizarUsuario.contrasenia()!=null) {
            this.contrasenia = datosActualizarUsuario.contrasenia();
        }
    }

    public void actualizarSolucion(Respuesta respuesta, Topico topico) {
        respuesta.setSolucion(true);
        topico.setEstatus(StatusTopico.SOLUCIONADO);
    }
}
