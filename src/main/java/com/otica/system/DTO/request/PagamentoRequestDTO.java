package com.otica.system.DTO.request;

import com.otica.system.model.TipoPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoRequestDTO {

    @NotNull(message="O id do pedido é obrigatório")
    private Long pedidoId;

    @NotNull(message="Tipo é obrigatório")
    private TipoPagamento tipo;

    @NotNull(message="Valor é obrigatório")
    @Positive(message="Valor tem que ser obrigatoriamente positivo.")
    private BigDecimal valor;
}
