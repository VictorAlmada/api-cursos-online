package com.victor.cursoonline.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.cursoonline.DTO.requests.AlunoRequestDTO;
import com.victor.cursoonline.DTO.responses.AlunoResponseDTO;
import com.victor.cursoonline.entities.Aluno;
import com.victor.cursoonline.services.AlunoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

	private final AlunoService alunoService;

	// LISTAR TODOS
	@GetMapping
	public ResponseEntity<List<AlunoResponseDTO>> findAll() {
		List<Aluno> alunos = alunoService.findAll();
		List<AlunoResponseDTO> dtos = alunos.stream()
				.map(this::toResponseDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	// BUSCAR POR ID
	@GetMapping("/{id}")
	public ResponseEntity<AlunoResponseDTO> findById(@PathVariable Long id) {
		Aluno aluno = alunoService.findById(id);
		return ResponseEntity.ok(toResponseDTO(aluno));
	}

	// SALVAR
	@PostMapping
	public ResponseEntity<AlunoResponseDTO> save(@RequestBody @Valid AlunoRequestDTO dto) {
		Aluno aluno = alunoService.save(dto);
		URI uri = URI.create("/alunos/" + aluno.getId());
		return ResponseEntity.created(uri).body(toResponseDTO(aluno));
	}
	
	// ATUALIZAR
	@PutMapping("/{id}")
	public ResponseEntity<AlunoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AlunoRequestDTO dto) {
		Aluno aluno = alunoService.update(id, dto);
		return ResponseEntity.ok(toResponseDTO(aluno));
	}

	// DELETAR
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		alunoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// ENTITY -> DTO
	private AlunoResponseDTO toResponseDTO(Aluno aluno) {
		return new AlunoResponseDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
	}

}
