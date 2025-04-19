package com.victor.cursoonline.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.victor.cursoonline.DTO.requests.AlunoRequestDTO;
import com.victor.cursoonline.entities.Aluno;
import com.victor.cursoonline.exceptions.ResourceNotFoundException;
import com.victor.cursoonline.repositories.AlunoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoService {
	
	private final AlunoRepository alunoRepository;
	
	// LISTAR TODOS
	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}
	
	// BUSCAR POR ID
	public Aluno findById(Long id) {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		return aluno.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + id));
	}
	
	// SALVAR
	@Transactional
	public Aluno save(AlunoRequestDTO dto) {
		Aluno aluno = new Aluno();
		aluno.setNome(dto.getNome());
		aluno.setEmail(dto.getEmail());
		return alunoRepository.save(aluno);
	}
	
	// ATUALIZAR
	@Transactional
	public Aluno update(long id, AlunoRequestDTO dto) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + id));
		aluno.setNome(dto.getNome());
		aluno.setEmail(dto.getEmail());
		return alunoRepository.save(aluno);
	}
	
	// DELETE
	public void delete(Long id) {
		alunoRepository.deleteById(id);
	}
	
	
	
}
