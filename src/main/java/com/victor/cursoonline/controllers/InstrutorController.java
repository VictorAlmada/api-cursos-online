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

import com.victor.cursoonline.DTO.requests.InstrutorRequestDTO;
import com.victor.cursoonline.DTO.responses.InstrutorResponseDTO;
import com.victor.cursoonline.entities.Instrutor;
import com.victor.cursoonline.services.InstrutorService;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

	@Autowired
	private InstrutorService instrutorService;

	// LISTAR TODOS
	@GetMapping
	public ResponseEntity<List<InstrutorResponseDTO>> findAll() {
		List<Instrutor> instrutores = instrutorService.findAll();
		List<InstrutorResponseDTO> dtos = instrutores.stream()
				.map(this::toResponseDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	// BUSCAR POR ID
	@GetMapping("/{id}")
	public ResponseEntity<InstrutorResponseDTO> findById(@PathVariable Long id) {
		Instrutor instrutor = instrutorService.findById(id);
		return ResponseEntity.ok(toResponseDTO(instrutor));
	}

	// SALVAR
	@PostMapping
	public ResponseEntity<InstrutorResponseDTO> save(@RequestBody InstrutorRequestDTO dto) {
		Instrutor instrutor = instrutorService.save(dto);
		return ResponseEntity.created(URI.create("/instrutores/" + instrutor.getId()))
				.body(toResponseDTO(instrutor));
	}

	// ATUALIZAR
	@PutMapping("/{id}")
	public ResponseEntity<InstrutorResponseDTO> update(@PathVariable Long id, @RequestBody InstrutorRequestDTO dto) {
		Instrutor instrutorAtualizado = instrutorService.update(id, dto);
		return ResponseEntity.ok(toResponseDTO(instrutorAtualizado));
	}

	// DELETAR
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		instrutorService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// ENTITY -> DTO
	private InstrutorResponseDTO toResponseDTO(Instrutor instrutor) {
		return new InstrutorResponseDTO(instrutor.getId(), instrutor.getNome(), instrutor.getEmail());
	}

}
