package br.ufma.sppg.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.sppg.controller.response.MessageResponse;
import br.ufma.sppg.controller.response.UserInfoResponse;
import br.ufma.sppg.domain.dto.CadastroDTO;
import br.ufma.sppg.domain.dto.LoginDTO;
import br.ufma.sppg.domain.model.Docente;
import br.ufma.sppg.domain.model.ERole;
import br.ufma.sppg.domain.model.Role;
import br.ufma.sppg.domain.model.User;
import br.ufma.sppg.repo.DocenteRepository;
import br.ufma.sppg.repo.RoleRepository;
import br.ufma.sppg.repo.UserRepository;
import br.ufma.sppg.security.jwt.JwtUtils;
import br.ufma.sppg.security.services.UserDetailsCustom;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginRequest) {

        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsCustom userDetails = (UserDetailsCustom) authentication.getPrincipal();

        // List<String> roles = userDetails.getAuthorities().stream()
        // .map(item -> item.getAuthority())
        // .collect(Collectors.toList());

        String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> registerUser(@RequestBody CadastroDTO signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        Docente doc = docenteRepository.findByLattes(signUpRequest.getLattes());

        // Create new user's account
        User user = User.builder().email(signUpRequest.getEmail()).username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword())).docente(doc).build();

        Set<String> strRoles = signUpRequest.getRoles();

        Set<Role> roles = new HashSet<>();

        // Atribui roles ao novo user
        if (strRoles == null) {

            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            for (String role : strRoles) {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                        roles.add(modRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                        roles.add(userRole);
                }

            }
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}