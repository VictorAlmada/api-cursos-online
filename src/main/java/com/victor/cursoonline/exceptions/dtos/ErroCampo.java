package com.victor.cursoonline.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErroCampo {
	private String campo;
	private String mensagem;
}
