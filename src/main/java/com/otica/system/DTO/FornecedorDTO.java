package com.otica.system.DTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {

    @Id
    private Long id;

    private String nome;
    private String telefone;
    private String email;
}
