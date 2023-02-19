package com.pdrsoft.pdrfood.domain.service;

import com.pdrsoft.pdrfood.domain.exception.CidadeNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pdrsoft.pdrfood.domain.exception.EntidadeEmUsoException;
import com.pdrsoft.pdrfood.domain.exception.EntidadeNaoEncontradaException;
import com.pdrsoft.pdrfood.domain.model.Cidade;
import com.pdrsoft.pdrfood.domain.model.Estado;
import com.pdrsoft.pdrfood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	public Cidade salvar(Cidade cidade) {

		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradoException(cidadeId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

	public Cidade buscarOuFalhar(Long cidadeId){
		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new CidadeNaoEncontradoException(cidadeId));
	}
	
}
