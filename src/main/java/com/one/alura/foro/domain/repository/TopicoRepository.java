package com.one.alura.foro.domain.repository;

import com.one.alura.foro.domain.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
