package com.victor.cursoonline.DTO.requests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatriculaRequestDTO {
	
	private LocalDate dataMatricula;
	
	@NotNull(message = "ID do aluno é obrigatório")
	private Long alunoId;
	
	@NotNull(message = "ID do curso é obrigatório")
	private Long cursoId;
	
}
