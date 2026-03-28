package com.otica.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otica.system.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}