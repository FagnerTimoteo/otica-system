package com.otica.system.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pagamento {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
    @JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipo;
	
	private BigDecimal valor;
	
	private Date dataPagamento;
	
	@Enumerated(EnumType.STRING)
	private StatusPagamento status;
}
