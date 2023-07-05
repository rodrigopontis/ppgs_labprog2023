package br.ufma.sppg.config;

import java.io.IOException;
import java.util.Enumeration;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
// Classe de filtro para autenticação de usuário
public class JwtAutenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            // Parametros nao podem ser nulos
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, 
            @NonNull FilterChain filterChain
            )

            throws ServletException, IOException {

            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                    return;
            }
        jwt = authHeader.substring(7);
    }
}
