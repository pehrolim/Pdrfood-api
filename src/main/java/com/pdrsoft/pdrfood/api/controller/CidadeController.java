package com.pdrsoft.pdrfood.api.controller;

import java.util.List;

import com.pdrsoft.pdrfood.domain.exception.EstadoNaoEncontradoException;
import com.pdrsoft.pdrfood.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.pdrsoft.pdrfood.domain.model.Cidade;
import com.pdrsoft.pdrfood.domain.repository.CidadeRepository;
import com.pdrsoft.pdrfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId) {
		return cadastroCidade.buscarOuFalhar(cidadeId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody @Validated Cidade cidade) {
		try{
			return cadastroCidade.salvar(cidade);
		}catch (EstadoNaoEncontradoException e){
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable Long cidadeId,
							 @RequestBody @Validated Cidade cidade) {

		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");

		try{
		return cadastroCidade.salvar(cidadeAtual);
		}catch (EstadoNaoEncontradoException e){
			throw new NegocioException(e.getMessage(), e);
		}

	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}




}
