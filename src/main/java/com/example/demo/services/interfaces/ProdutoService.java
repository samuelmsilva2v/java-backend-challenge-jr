package com.example.demo.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;

public interface ProdutoService {

	ProdutoResponseDto cadastrar(ProdutoRequestDto request);
	
	ProdutoResponseDto atualizar(UUID id, ProdutoRequestDto request);
	
	String deletar(UUID id);
	
	ProdutoResponseDto consultarPorId(UUID id);
	
	List<ProdutoResponseDto> listar();
}
