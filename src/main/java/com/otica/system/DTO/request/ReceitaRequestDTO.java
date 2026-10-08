package com.otica.system.DTO.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReceitaRequestDTO {

    @NotNull(message = "Id de Cliente é obrigatório")
    private Long clienteId;

    @NotNull(message="esferaOD é obrigatório")
    private BigDecimal esferaOD;

    @NotNull(message="esferaOE é obrigatório")
    private BigDecimal esferaOE;

    @NotNull(message="cilindroOD é obrigatório")
    private BigDecimal cilindroOD;

    @NotNull(message="cilindroOE é obrigatório")
    private BigDecimal  cilindroOE;

    @DecimalMin(value = "0.0", message = "Eixo deve ser maior ou igual a 0")
    @DecimalMax(value = "180.0", message = "Eixo deve ser menor ou igual a 180")
    private BigDecimal  eixoOD;

    @DecimalMin(value = "0.0", message = "Eixo deve ser maior ou igual a 0")
    @DecimalMax(value = "180.0", message = "Eixo deve ser menor ou igual a 180")
    private BigDecimal eixoOE;

    @NotNull(message = "Distância pupilar é obrigatória")
    @Positive(message = "Distância pupilar deve ser positiva")
    private Double distanciaPupilar;

    private String observacoes;
}
