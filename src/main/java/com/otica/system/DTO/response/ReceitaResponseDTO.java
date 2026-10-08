package com.otica.system.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaResponseDTO {

    private Long id;

    private Long clienteId;

    private Double esferaOD;
    private Double esferaOE;

    private Double cilindroOD;
    private Double cilindroOE;

    private Double eixoOD;
    private Double eixoOE;

    private Double distanciaPupilar;
    private String observacoes;
    private LocalDateTime dataReceita;
}
