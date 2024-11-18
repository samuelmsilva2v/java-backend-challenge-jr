package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.enums.TipoProduto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoRequestDto {

	@NotBlank(message = "O nome do produto é obrigatório.")
	@Size(min = 3, max = 255, message = "O nome do produto deve ter entre 3 e 255 caracteres.")
	private String nome;
	
	@NotNull(message = "O tipo do produto é obrigatório.")
	private TipoProduto tipo;
	
	@NotNull(message = "O preço unitário é obrigatório.")
	@DecimalMin(value = "0.01", inclusive = true, message = "O preço unitário deve ser maior que zero.")
	private BigDecimal precoUnitario;
}
