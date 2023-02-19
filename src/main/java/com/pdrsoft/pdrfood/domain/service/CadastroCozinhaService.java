package com.pdrsoft.pdrfood.domain.service;

import com.pdrsoft.pdrfood.domain.exception.CozinhaNaoEncontradoException;
import com.pdrsoft.pdrfood.domain.exception.EntidadeEmUsoException;
import com.pdrsoft.pdrfood.domain.exception.EntidadeNaoEncontradaException;
import com.pdrsoft.pdrfood.domain.model.Cozinha;
import com.pdrsoft.pdrfood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

	public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradoException(cozinhaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
	}

	public Cozinha buscarOuFalhar(Long cozinhaId){
		return cozinhaRepository.findById(cozinhaId).orElseThrow(
				() -> new CozinhaNaoEncontradoException(cozinhaId));
	}

}
