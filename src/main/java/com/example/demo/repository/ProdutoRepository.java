package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID>{

}
