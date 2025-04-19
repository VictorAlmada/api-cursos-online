package com.victor.cursoonline.DTO.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CursoRequestDTO {
	
	@NotBlank(message = "Nome do curso é obrigatório")
	private String nome;
	
	@NotBlank(message = "Descrição é obrigatória")
	private String descricao;
	
	@NotNull(message = "ID do instrutor é obrigatório")
	private Long instrutorId;
	
}
