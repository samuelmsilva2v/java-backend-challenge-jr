package com.example.demo.dto;

import com.example.demo.enums.TipoProduto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardResponseDto {

    private TipoProduto tipo;
    private Long quantidade;
    private Double precoMedio;
}
