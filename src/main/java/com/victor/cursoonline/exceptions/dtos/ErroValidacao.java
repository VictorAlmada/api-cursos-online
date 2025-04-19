package com.victor.cursoonline.exceptions.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadrao {
	private List<ErroCampo> erros = new ArrayList<>();
	
	public ErroValidacao(Instant timestamp, Integer status, String erro, String mensagem, String caminho) {
		super(timestamp, status, erro, mensagem, caminho);
	}
	
	public void addErro(String campo, String mensagem) {
		erros.add(new ErroCampo(campo, mensagem));
	}
	
	public List<ErroCampo> getErros() {
		return erros;
	}
}
