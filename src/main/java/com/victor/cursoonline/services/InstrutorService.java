package com.victor.cursoonline.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.cursoonline.DTO.requests.InstrutorRequestDTO;
import com.victor.cursoonline.entities.Instrutor;
import com.victor.cursoonline.exceptions.ResourceNotFoundException;
import com.victor.cursoonline.repositories.InstrutorRepository;

@Service
public class InstrutorService {
	
	@Autowired
	private InstrutorRepository instrutorRepository;
	
	// LISTAR TODOS
	public List<Instrutor> findAll() {
		return instrutorRepository.findAll();
	}
	
	// BUSCAR POR ID
	public Instrutor findById(Long id) {
		Optional<Instrutor> instrutor = instrutorRepository.findById(id);
		return instrutor.orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado com ID: " + id));
	}
	
	// SALVAR
	public Instrutor save(InstrutorRequestDTO dto) {
		Instrutor instrutor = new Instrutor();
		instrutor.setNome(dto.getNome());
		instrutor.setEmail(dto.getEmail());
		return instrutorRepository.save(instrutor);
	}
	
	// ATUALIZAR
	public Instrutor update(Long id, InstrutorRequestDTO dto) {
		Instrutor instrutor = instrutorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado com ID:" + id));
		instrutor.setNome(dto.getNome());
		instrutor.setEmail(dto.getEmail());
		return instrutorRepository.save(instrutor);
	}
	
	// DELETAR
	public void delete(Long id) {
		instrutorRepository.deleteById(id);
	}
	
	
}
