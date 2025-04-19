package com.victor.cursoonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.cursoonline.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
}
