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
public class ClienteResponseDTO {

    private Long id;

    private String nome;
    private String cpf;
    private String telefone;

    private String email;
    private LocalDateTime dataCadastro;
}