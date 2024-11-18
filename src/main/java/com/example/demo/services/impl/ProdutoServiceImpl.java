package com.example.demo.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DashboardResponseDto;
import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.entities.Produto;
import com.example.demo.exceptions.ResourceNotFoundException;
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

		var produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
		produto.setNome(request.getNome());
		produto.setTipo(request.getTipo());
		produto.setPrecoUnitario(BigDecimal.valueOf(request.getPrecoUnitario()));

		produtoRepository.save(produto);

		var response = modelMapper.map(produto, ProdutoResponseDto.class);

		return response;
	}

	@Override
	public String deletar(UUID id) {

		var produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));

		produtoRepository.delete(produto);

		return "Produto excluído com sucesso!";
	}

	@Override
	public ProdutoResponseDto consultarPorId(UUID id) {

		var produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));

		var response = modelMapper.map(produto, ProdutoResponseDto.class);

		return response;
	}

	@Override
	public List<ProdutoResponseDto> listar() {

		return produtoRepository.findAll().stream().map(produto -> modelMapper.map(produto, ProdutoResponseDto.class))
				.collect(Collectors.toList());
	}

	public List<DashboardResponseDto> calcularDashboard() {
		return produtoRepository.calcularDashboard();
	}
}
