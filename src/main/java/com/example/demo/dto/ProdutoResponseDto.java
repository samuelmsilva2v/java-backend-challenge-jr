package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.demo.enums.TipoProduto;

import lombok.Data;

@Data
public class ProdutoResponseDto {

	private UUID id;
	private String nome;
	private TipoProduto tipo;
	private BigDecimal precoUnitario;
}
