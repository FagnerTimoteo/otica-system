package com.otica.system.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private Boolean ativo;
	
	@OneToMany(mappedBy = "funcionario")
	private List<Pedido> pedidos;
}
