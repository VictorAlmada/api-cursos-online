package com.victor.cursoonline.DTO.responses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatriculaResponseDTO {
	private Long id;
	private LocalDate dataMatricula;
	private String nomeAluno;
	private String nomeCurso;
}
