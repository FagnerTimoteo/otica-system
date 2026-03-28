package com.otica.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otica.system.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}