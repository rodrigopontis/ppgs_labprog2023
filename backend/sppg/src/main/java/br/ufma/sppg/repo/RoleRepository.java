package br.ufma.sppg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.sppg.domain.model.ERole;
import br.ufma.sppg.domain.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}