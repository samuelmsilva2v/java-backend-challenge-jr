package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.entities.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.services.interfaces.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProdutoResponseDto cadastrar(ProdutoRequestDto request) {

		var produto = modelMapper.map(request, Produto.class);
		produto.setId(UUID.randomUUID());
		produtoRepository.save(produto);

		var response = modelMapper.map(produto, ProdutoResponseDto.class);

		return response;
	}

	@Override
	public ProdutoResponseDto atualizar(UUID id, ProdutoRequestDto request) {

		var produto = produtoRepository.findById(id).get(); // TODO EntityNotFoundException mapear a exceção para retornar o status HTTP correto
		produto.setNome(request.getNome());
		produto.setTipo(request.getTipo());
		produto.setPrecoUnitario(request.getPrecoUnitario());

		produtoRepository.save(produto);

		var response = modelMapper.map(produto, ProdutoResponseDto.class);

		return response;
	}

	@Override
	public String deletar(UUID id) {

		var produto = produtoRepository.findById(id).get(); // TODO EntityNotFoundException mapear a exceção para retornar o status HTTP correto

		produtoRepository.delete(produto);

		return "Produto excluído com sucesso!";
	}

	@Override
	public ProdutoResponseDto consultarPorId(UUID id) {

		var produto = produtoRepository.findById(id).get(); // TODO EntityNotFoundException mapear a exceção para retornar o status HTTP correto

		var response = modelMapper.map(produto, ProdutoResponseDto.class);

		return response;
	}

	@Override
	public List<ProdutoResponseDto> listar() {

		var response = new ArrayList<ProdutoResponseDto>();

		for (var produto : produtoRepository.findAll()) {

			var produtoResponse = modelMapper.map(produto, ProdutoResponseDto.class);

			response.add(produtoResponse);
		}

		return response;
	}
}
