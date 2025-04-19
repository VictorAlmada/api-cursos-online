package com.victor.cursoonline.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victor.cursoonline.entities.Aluno;
import com.victor.cursoonline.entities.Curso;
import com.victor.cursoonline.entities.Instrutor;
import com.victor.cursoonline.entities.Matricula;
import com.victor.cursoonline.repositories.AlunoRepository;
import com.victor.cursoonline.repositories.CursoRepository;
import com.victor.cursoonline.repositories.InstrutorRepository;
import com.victor.cursoonline.repositories.MatriculaRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class TestConfig implements CommandLineRunner {
	
	private final CursoRepository cursoRepository;
	private final InstrutorRepository instrutorRepository;
	private final AlunoRepository alunoRepository;
	private final MatriculaRepository matriculaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// Criando instrutores
        Instrutor i1 = new Instrutor(null, "Ana Costa", "ana@email.com", null);
        Instrutor i2 = new Instrutor(null, "Carlos Silva", "carlos@email.com", null);
        instrutorRepository.saveAll(List.of(i1, i2));
		
        // Criando cursos e associando aos instrutores
		Curso c1 = new Curso(null, "Java Web", "Curso completo de Java com Spring Boot", i1);
		Curso c2 = new Curso(null, "JavaScript", "Curso de JavaScript do básico ao avançado.", i2);
		Curso c3 = new Curso(null, "Python para Análise de Dados", "Curso focado em Data Science", i1);
		cursoRepository.saveAll(List.of(c1, c2, c3));
		
		// Criando alunos
		Aluno a1 = new Aluno(null, "João Mendes", "joao@email.com", null);
		Aluno a2 = new Aluno(null, "Maria Lima", "maria@email.com", null);
		alunoRepository.saveAll(List.of(a1, a2));
		
		// Criando matriculas
		Matricula mat1 = new Matricula(null, LocalDate.now(), a1, c1);
		Matricula mat2 = new Matricula(null, LocalDate.now(), a1, c2);
		Matricula mat3 = new Matricula(null, LocalDate.now(), a2, c3);
		matriculaRepository.saveAll(List.of(mat1, mat2, mat3));
		
		
	}
	
}
