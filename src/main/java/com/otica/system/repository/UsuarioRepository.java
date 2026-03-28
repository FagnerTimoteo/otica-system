package com.otica.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otica.system.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}