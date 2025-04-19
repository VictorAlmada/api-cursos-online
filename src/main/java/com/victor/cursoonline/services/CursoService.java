package com.victor.cursoonline.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.cursoonline.DTO.requests.CursoRequestDTO;
import com.victor.cursoonline.entities.Curso;
import com.victor.cursoonline.entities.Instrutor;
import com.victor.cursoonline.exceptions.ResourceNotFoundException;
import com.victor.cursoonline.repositories.CursoRepository;
import com.victor.cursoonline.repositories.InstrutorRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private InstrutorRepository instrutorRepository;
	
	// LISTAR TODOS
	public List<Curso> findAll() {
		return cursoRepository.findAll();
	}
	
	// BUSCAR POR ID
	public Curso findById(Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		return curso.orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado."));
	}
	
	// SALVAR
	public Curso save(CursoRequestDTO dto) {
		Curso curso = new Curso();
		curso.setNome(dto.getNome());
		curso.setDescricao(dto.getDescricao());
		Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorId())
				.orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado"));
		curso.setInstrutor(instrutor);
		return cursoRepository.save(curso);
	}
	
	// ATUALIZAR
	public Curso update(Long id, CursoRequestDTO dto) {
		Curso curso = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
		curso.setNome(dto.getNome());
		curso.setDescricao(dto.getDescricao());
		Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorId())
				.orElseThrow(() -> new ResourceNotFoundException("Instrutor não encontrado"));
		curso.setInstrutor(instrutor);
		return cursoRepository.save(curso);
	}
	
	// DELETAR
	public void delete(Long id) {
		cursoRepository.deleteById(id);
	}
	
	
}
