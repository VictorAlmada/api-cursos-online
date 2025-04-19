package com.victor.cursoonline.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.victor.cursoonline.DTO.requests.MatriculaRequestDTO;
import com.victor.cursoonline.entities.Aluno;
import com.victor.cursoonline.entities.Curso;
import com.victor.cursoonline.entities.Matricula;
import com.victor.cursoonline.exceptions.ResourceNotFoundException;
import com.victor.cursoonline.repositories.AlunoRepository;
import com.victor.cursoonline.repositories.CursoRepository;
import com.victor.cursoonline.repositories.MatriculaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatriculaService {
	
	private final MatriculaRepository matriculaRepository;
	private final AlunoRepository alunoRepository;
	private final CursoRepository cursoRepository;
	
	// LISTAR TODOS
	public List<Matricula> findAll() {
		return matriculaRepository.findAll();
	}
	
	// BUSCAR POR ID
	public Matricula findById(Long id) {
		Optional<Matricula> matricula = matriculaRepository.findById(id);
		return matricula.orElseThrow(() -> new ResourceNotFoundException("Matricula não encontrada."));
	}
	
	// SALVAR
	@Transactional
	public Matricula save(MatriculaRequestDTO dto) {
		Matricula matricula = new Matricula();
		matricula.setDataMatricula(dto.getDataMatricula());
		
		Aluno aluno = alunoRepository.findById(dto.getAlunoId()).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
		Curso curso = cursoRepository.findById(dto.getCursoId()).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
		
		matricula.setAluno(aluno);
		matricula.setCurso(curso);
		
		return matriculaRepository.save(matricula);
	}
	
	// ATUALIZAR
	@Transactional
	public Matricula update(Long id, MatriculaRequestDTO dto) {
		Matricula matricula = findById(id);
		matricula.setDataMatricula(dto.getDataMatricula());
		
		Aluno aluno = alunoRepository.findById(dto.getAlunoId()).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
		Curso curso = cursoRepository.findById(dto.getCursoId()).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
		
		matricula.setAluno(aluno);
		matricula.setCurso(curso);
		
		return matriculaRepository.save(matricula);
	}
	
	// DELETAR
	public void delete(Long id) {
		matriculaRepository.deleteById(id);
	}
	
	
	
	
}
