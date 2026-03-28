package com.otica.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otica.system.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}