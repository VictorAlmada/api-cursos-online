package com.victor.cursoonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.cursoonline.entities.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

}
