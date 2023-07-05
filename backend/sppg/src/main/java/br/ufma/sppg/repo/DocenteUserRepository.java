package br.ufma.sppg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufma.sppg.model.DocenteUser;

@Repository
public interface DocenteUserRepository extends JpaRepository<DocenteUser, Integer>{
    Optional<DocenteUser> findByEmail(String email);

    
}
