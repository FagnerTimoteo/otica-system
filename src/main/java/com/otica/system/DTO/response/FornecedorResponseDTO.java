package com.otica.system.DTO.response;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorResponseDTO {

    @Id
    private Long id;

    private String nome;
    private String telefone;
    private String email;
}
