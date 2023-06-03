package com.one.alura.foro.domain.repository;

import com.one.alura.foro.domain.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
