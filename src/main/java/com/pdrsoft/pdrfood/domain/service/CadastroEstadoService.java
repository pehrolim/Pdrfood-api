package com.pdrsoft.pdrfood.domain.service;

import com.pdrsoft.pdrfood.domain.exception.EstadoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pdrsoft.pdrfood.domain.exception.EntidadeEmUsoException;
import com.pdrsoft.pdrfood.domain.exception.EntidadeNaoEncontradaException;
import com.pdrsoft.pdrfood.domain.model.Estado;
import com.pdrsoft.pdrfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	public final String MSG_ESTADO_EM_USO = "Estado de código %d não pode serremovido, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}

	public Estado buscarOuFalhar(Long estadoId){
		return estadoRepository.findById(estadoId).orElseThrow(
				() -> new EstadoNaoEncontradoException(estadoId));
	}
	
}
