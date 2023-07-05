package br.ufma.sppg.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufma.sppg.repo.DocenteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DocenteUserService implements UserDetailsService{
    
    private final DocenteRepository docenteRepository;
    private final static String USER_NOT_FOUND_MSG = "user with email %s nao existe";
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        return docenteRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG))); 
        
    }
    
}
