package br.ufma.sppg.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class DocenteUser implements UserDetails {
    @Id
    @SequenceGenerator(
        name = "docente_sequence",
        sequenceName = "docente_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE ,
        generator = "docente_sequence"
    )
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private DocenteUserRole DocenteUserRole;
    private Boolean locked; // verificar se a conta esta bloqueada
    private Boolean enabled;


    
    public DocenteUser(String name, String username, String email, String password,
            br.ufma.sppg.model.DocenteUserRole docenteUserRole, Boolean locked, Boolean enabled) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        DocenteUserRole = docenteUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(DocenteUserRole.name());
        return Collections.singletonList(authority);

        // throw new UnsupportedOperationException("Unimplemented method
        // 'getAuthorities'");
    }

    @Override
    public String getPassword() {
        return password;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getPassword'");
    }

    @Override
    public String getUsername() {
        return username;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getUsername'");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'isAccountNonExpired'");
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'isAccountNonLocked'");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'isCredentialsNonExpired'");
    }

    @Override
    public boolean isEnabled() {
        return enabled;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }

}
