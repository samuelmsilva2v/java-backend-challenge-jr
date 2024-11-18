package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.DashboardResponseDto;
import com.example.demo.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

	@Query("SELECT new com.example.demo.dto.DashboardResponseDto(p.tipo, COUNT(p), AVG(p.precoUnitario)) "
			+ "FROM Produto p GROUP BY p.tipo")
	List<DashboardResponseDto> calcularDashboard();
}
