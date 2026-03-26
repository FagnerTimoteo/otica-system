package com.otica.system.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Receita {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	 
	@ManyToOne
    @JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	private Double esferaOD;
	
	private Double cilindroOD;
	
	private Double eixoOD;
	
	private Double esferaOE;
	
	private Double cilindroOE;
	
	private Double eixoOE;
	
	private Double distanciaPupilar;
	
	private String observacoes;
	
	private LocalDateTime dataReceita;
}
