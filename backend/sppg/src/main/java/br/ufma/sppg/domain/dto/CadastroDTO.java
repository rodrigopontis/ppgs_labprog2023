package br.ufma.sppg.domain.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CadastroDTO {
    private String username;
    private String lattes;
    private String password;
    private String email;
    private Set<String> roles;
}