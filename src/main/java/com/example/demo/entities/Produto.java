package com.example.demo.entities;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.demo.enums.TipoProduto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_produto")
@Data
public class Produto {

	@Id
	private UUID id;
	private String nome;

	@Enumerated(EnumType.STRING)
	private TipoProduto tipo;
	private BigDecimal precoUnitario;
}
