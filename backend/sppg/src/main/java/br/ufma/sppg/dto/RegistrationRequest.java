package br.ufma.sppg.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    public private final String firstName;
    public private final String lastName;
    private final String email;
    public private final String password;
    

    
}
