package com.victor.cursoonline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.cursoonline.entities.Instrutor;

@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
	
	Optional<Instrutor> findByNome(String nome);
	
}
