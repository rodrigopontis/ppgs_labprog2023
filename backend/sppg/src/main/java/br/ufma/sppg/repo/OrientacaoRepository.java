package br.ufma.sppg.repo;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a
import org.springframework.data.jpa.repository.JpaRepository;
import br.ufma.sppg.model.Orientacao;

public interface OrientacaoRepository extends JpaRepository<Orientacao, Integer> {
    List<Orientacao> findAllById(Integer id);

    @Query("SELECT o FROM Orientacao o JOIN o.orientador d JOIN d.programas p where p.id = :idPrograma AND o.ano >= :anoInicio AND o.ano<= :anoFim")
    Optional<List<Orientacao>> findByPPG(Integer idPrograma, Integer anoInicio, Integer anoFim);

    @Query("SELECT o FROM Orientacao o JOIN o.orientador d WHERE d.id = :idDocente AND o.ano >= :anoInicio AND o.ano<= :anoFim")
<<<<<<< HEAD
    Optional<List<Orientacao>> findByDocente(Integer idDocente, Integer anoInicio, Integer anofim);

    

    @Query("SELECT o FROM Orientacao o JOIN o.orientador d JOIN d.programas p where p.id = :idPrograma")
    Optional<List<Orientacao>> findByPPG(Integer idPrograma);
=======
    Optional<List<Orientacao>> findByDocente(Integer idDocente, Integer anoInicio, Integer anoFim);
>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a
}
