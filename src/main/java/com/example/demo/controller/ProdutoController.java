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

import com.example.demo.dto.DashboardResponseDto;
import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.services.impl.ProdutoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoServiceImpl produtoService;
	
	@PostMapping
	public ProdutoResponseDto insert(@RequestBody @Valid ProdutoRequestDto request) {
		return produtoService.cadastrar(request);
	}
	
	@PutMapping("{id}")
	public ProdutoResponseDto update(@PathVariable UUID id, @RequestBody @Valid ProdutoRequestDto request) {
		return produtoService.atualizar(id, request);
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable UUID id) {
		return produtoService.deletar(id);
	}
	
	@GetMapping
	public List<ProdutoResponseDto> getAll() {
		return produtoService.listar();
	}
	
	@GetMapping("{id}")
	public ProdutoResponseDto getById(@PathVariable UUID id) {
		return produtoService.consultarPorId(id);
	}
	
	@GetMapping("/dashboard")
    public List<DashboardResponseDto> getDashboard() {
        return produtoService.calcularDashboard();
    }
}
