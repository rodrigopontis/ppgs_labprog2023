package br.ufma.sppg.repo;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
=======
import java.util.List;
>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufma.sppg.model.*;

<<<<<<< HEAD
public interface ProgramaRepository 
    extends JpaRepository<Programa, Integer> {
        List<Programa> findAllByNome(String nomePPG);

        @Query("select p.docentes from Programa p where p.id = :idPPG")
        List<Docente> obterDocentes(@Param("idPPG") Integer idPPG);
=======
public interface ProgramaRepository
        extends JpaRepository<Programa, Integer> {
    List<Programa> findAllByNome(String nomePPG);

    @Query("select p.docentes from Programa p where p.id = :idPPG")
    List<Docente> obterDocentes(@Param("idPPG") Integer idPPG);
>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a
}
