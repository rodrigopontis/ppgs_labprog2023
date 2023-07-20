package br.ufma.sppg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.sppg.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
