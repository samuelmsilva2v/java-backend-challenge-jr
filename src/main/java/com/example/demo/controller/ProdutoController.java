package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.services.impl.ProdutoServiceImpl;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;
	
	@PostMapping
	public ProdutoResponseDto insert(@RequestBody ProdutoRequestDto request) {
		return produtoServiceImpl.cadastrar(request);
	}
	
	@PutMapping("{id}")
	public ProdutoResponseDto update(@PathVariable UUID id, @RequestBody ProdutoRequestDto request) {
		return produtoServiceImpl.atualizar(id, request);
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable UUID id) {
		return produtoServiceImpl.deletar(id);
	}
	
	@GetMapping
	public List<ProdutoResponseDto> getAll() {
		return produtoServiceImpl.listar();
	}
	
	@GetMapping("{id}")
	public ProdutoResponseDto getById(@PathVariable UUID id) {
		return produtoServiceImpl.consultarPorId(id);
	}
}
