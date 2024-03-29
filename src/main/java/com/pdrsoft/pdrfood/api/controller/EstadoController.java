package com.pdrsoft.pdrfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pdrsoft.pdrfood.domain.exception.EntidadeEmUsoException;
import com.pdrsoft.pdrfood.domain.exception.EntidadeNaoEncontradaException;
import com.pdrsoft.pdrfood.domain.model.Estado;
import com.pdrsoft.pdrfood.domain.repository.EstadoRepository;
import com.pdrsoft.pdrfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class  EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public Estado buscar(@PathVariable Long estadoId) {
		return cadastroEstado.buscarOuFalhar(estadoId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody @Validated Estado estado) {
		return cadastroEstado.salvar(estado);
	}
	
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable Long estadoId,
			@RequestBody @Validated Estado estado) {

		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

		BeanUtils.copyProperties(estado, estadoAtual, "id");
			
		return cadastroEstado.salvar(estadoAtual);

	}
	
	@DeleteMapping("/{estadoId}")
	public void remover(@PathVariable Long estadoId) {
			cadastroEstado.excluir(estadoId);
	}
	
}
