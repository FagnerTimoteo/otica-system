package com.otica.system.DTO.response;

import com.otica.system.model.StatusPagamento;
import com.otica.system.model.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoResponseDTO {

    @Id
    private Long id;
    private Long pedidoId;
    private TipoPagamento tipo;
    private BigDecimal valor;
    private Date dataPagamento;
    private StatusPagamento status;
}
