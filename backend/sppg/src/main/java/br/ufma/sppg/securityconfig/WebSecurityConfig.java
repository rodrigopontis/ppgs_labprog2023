package br.ufma.sppg.securityconfig;

import java.util.concurrent.ExecutionException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import br.ufma.sppg.model.DocenteUser;
import br.ufma.sppg.service.DocenteUserService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final DocenteUserService docenteUserService;
    @Override
    protected void configure(HttpSecurity http) throws Exeption {
        http
            .csrf().disable()  //permite enviar post request sem ser rejeitado so pra testar
            .authorizeRequests()
                .antMatchers().permitall()
                .anyRequest()
                .authenticated().and().formLogin();
    }

    @Override
    protect void configure(AuthenticationManagerBuilder.
    auth.authenticationProvider() ){
        auth.authenticationProvide()
    }

    @Bean

}
