package com.pdrsoft.pdrfood.domain.exception;


public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;


	public CozinhaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public CozinhaNaoEncontradoException(Long cozinhaId) {
		 this(String.format("Não existe um cadastro de estado com código %d", cozinhaId));
	}
}
