package com.victor.cursoonline.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.cursoonline.DTO.requests.MatriculaRequestDTO;
import com.victor.cursoonline.DTO.responses.MatriculaResponseDTO;
import com.victor.cursoonline.entities.Matricula;
import com.victor.cursoonline.services.MatriculaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
	
	@Autowired
	private MatriculaService matriculaService;
	
	// LISTAR TODOS
		@GetMapping
		public ResponseEntity<List<MatriculaResponseDTO>> findAll() {
			List<Matricula> matriculas = matriculaService.findAll();
			List<MatriculaResponseDTO> dtos = matriculas.stream()
					.map(this::toResponseDTO).collect(Collectors.toList());
			return ResponseEntity.ok(dtos);
		}
		
		// BUSCAR POR ID
		@GetMapping("/{id}")
		public ResponseEntity<MatriculaResponseDTO> findById(@PathVariable Long id) {
			Matricula matricula = matriculaService.findById(id);
			return ResponseEntity.ok(toResponseDTO(matricula));
		}
		
		// SALVAR
		@PostMapping
		public ResponseEntity<MatriculaResponseDTO> save(@RequestBody @Valid MatriculaRequestDTO dto) {
			Matricula matricula = matriculaService.save(dto);
			return ResponseEntity.created(URI.create("/matriculas/" + matricula.getId())).body(toResponseDTO(matricula));
		}
		
		// ATUALIZAR
		@PutMapping("/{id}")
		public ResponseEntity<MatriculaResponseDTO> update(@PathVariable Long id, @RequestBody @Valid MatriculaRequestDTO dto) {
			Matricula matricula = matriculaService.update(id, dto);
			return ResponseEntity.ok(toResponseDTO(matricula));
		}
		
		// DELETAR
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			matriculaService.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		public MatriculaResponseDTO toResponseDTO(Matricula matricula) {
			return new MatriculaResponseDTO(
					matricula.getId(),
					matricula.getDataMatricula(),
					matricula.getAluno().getNome(),
					matricula.getCurso().getNome());
		}

}
