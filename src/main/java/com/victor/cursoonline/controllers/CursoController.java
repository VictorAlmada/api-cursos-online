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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.cursoonline.DTO.requests.CursoRequestDTO;
import com.victor.cursoonline.DTO.responses.CursoResponseDTO;
import com.victor.cursoonline.entities.Curso;
import com.victor.cursoonline.services.CursoService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/cursos")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	// LISTAR TODOS
	@GetMapping
	public ResponseEntity<List<CursoResponseDTO>> findAll() {
		List<Curso> cursos = cursoService.findAll();
		List<CursoResponseDTO> dtos = cursos.stream()
				.map(this::toResponseDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
	
	// BUSCAR POR ID
	@GetMapping("/{id}")
	public ResponseEntity<CursoResponseDTO> findById(@PathVariable Long id) {
		Curso curso = cursoService.findById(id);
		return ResponseEntity.ok(toResponseDTO(curso));
	}
	
	// SALVAR
	@PostMapping
	public ResponseEntity<CursoResponseDTO> save(@RequestBody @Valid CursoRequestDTO dto) {
		Curso curso = cursoService.save(dto);
		CursoResponseDTO responseDTO = toResponseDTO(curso);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(uri).body(responseDTO);
		
	}
	
	// ATUALIZAR
	@PutMapping("/{id}")
	public ResponseEntity<CursoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid CursoRequestDTO dto) {
		Curso cursoAtualizado = cursoService.update(id, dto);
		return ResponseEntity.ok(toResponseDTO(cursoAtualizado));
	}
	
	// DELETAR
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cursoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	// ENTITY -> DTO
	private CursoResponseDTO toResponseDTO(Curso curso) {
		CursoResponseDTO dto = new CursoResponseDTO();
		dto.setId(curso.getId());
		dto.setNome(curso.getNome());
		dto.setDescricao(curso.getDescricao());
		dto.setNomeInstrutor(curso.getInstrutor().getNome());
		return dto;
	}
}
