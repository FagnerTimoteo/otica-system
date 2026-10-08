package com.otica.system.DTO.response;

import com.otica.system.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

        private Long id;

        private String nome;
        private String email;

        private Role role;
        private Boolean ativo;
}
