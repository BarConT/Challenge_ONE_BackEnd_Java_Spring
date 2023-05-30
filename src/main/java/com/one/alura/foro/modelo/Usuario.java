package com.one.alura.foro.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.one.alura.foro.dto.DatosActualizarUsuario;
import com.one.alura.foro.dto.DatosRegistroUsuario;
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
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Topico> topicos;

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
}
