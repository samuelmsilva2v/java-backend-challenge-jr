package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.entities.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.services.interfaces.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public ProdutoResponseDto cadastrar(ProdutoRequestDto request) {
		
		var produto = new Produto();
		produto.setId(UUID.randomUUID());
		produto.setNome(request.getNome());
		produto.setTipo(request.getTipo());
		produto.setPrecoUnitario(request.getPrecoUnitario());
		
		produtoRepository.save(produto);
		
		var response = new ProdutoResponseDto();
		response.setId(produto.getId());
		response.setNome(produto.getNome());
		response.setTipo(produto.getTipo());
		response.setPrecoUnitario(produto.getPrecoUnitario());
		
		return response;
	}

	@Override
	public ProdutoResponseDto atualizar(UUID id, ProdutoRequestDto request) {
		
		var produto = produtoRepository.findById(id).get();
		produto.setNome(request.getNome());
		produto.setTipo(request.getTipo());
		produto.setPrecoUnitario(request.getPrecoUnitario());
		
		produtoRepository.save(produto);
		
		var response = new ProdutoResponseDto();
		response.setId(produto.getId());
		response.setNome(produto.getNome());
		response.setTipo(produto.getTipo());
		response.setPrecoUnitario(produto.getPrecoUnitario());
		
		return response;
	}

	@Override
	public ProdutoResponseDto deletar(UUID id) {
	
		var produto = produtoRepository.findById(id).get();
		
		produtoRepository.delete(produto);
		
		var response = new ProdutoResponseDto();
		response.setId(produto.getId());
		response.setNome(produto.getNome());
		response.setTipo(produto.getTipo());
		response.setPrecoUnitario(produto.getPrecoUnitario());
		
		return response;
	}

	@Override
	public ProdutoResponseDto consultarPorId(UUID id) {
		
		var produto = produtoRepository.findById(id).get();
		
		var response = new ProdutoResponseDto();
		response.setId(produto.getId());
		response.setNome(produto.getNome());
		response.setTipo(produto.getTipo());
		response.setPrecoUnitario(produto.getPrecoUnitario());
		
		return response;
	}

	@Override
	public List<ProdutoResponseDto> listar() {
		
		var response = new ArrayList<ProdutoResponseDto>();
		
		for (var produto : produtoRepository.findAll()) {
			
			var produtoResponse = new ProdutoResponseDto();
			produtoResponse.setId(produto.getId());
			produtoResponse.setNome(produto.getNome());
			produtoResponse.setTipo(produto.getTipo());
			produtoResponse.setPrecoUnitario(produto.getPrecoUnitario());
			
			response.add(produtoResponse);
		}
		
		return response;
	}

	
}
