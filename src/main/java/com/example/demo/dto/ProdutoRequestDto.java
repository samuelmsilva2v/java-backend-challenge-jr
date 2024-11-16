package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.enums.TipoProduto;

import lombok.Data;

@Data
public class ProdutoRequestDto {

	private String nome;
	private TipoProduto tipo;
	private BigDecimal precoUnitario;
}
