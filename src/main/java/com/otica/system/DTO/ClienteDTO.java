package com.otica.system.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;

    private String nome;
    private String cpf;
    private String telefone;

    private String email;
    private LocalDateTime dataCadastro;
}