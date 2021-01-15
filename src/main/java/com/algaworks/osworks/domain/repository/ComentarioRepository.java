package com.algaworks.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.osworks.api.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
