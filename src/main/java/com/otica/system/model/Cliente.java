package com.otica.system.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    private String cpf;
    
    private String telefone;
    
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente")
    private List<Receita> receitas;
    
    private String email;
    
    private LocalDateTime dataCadastro;
}