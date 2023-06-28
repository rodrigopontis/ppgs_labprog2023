package br.ufma.sppg.repo;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufma.sppg.model.Tecnica;

public interface TecnicaRepository extends JpaRepository<Tecnica, Integer> {
    Optional<Tecnica> findById(Integer idTecnica);
<<<<<<< HEAD

    Boolean existsById(Integer idTecnica);

    @Query("SELECT t FROM Tecnica t join Docente d where d.id = :docenteId and t.ano >= :anoInicio and t.ano <= :anoFim")
    List<Tecnica> obterTecnicasDocente(Integer docenteId, Integer anoInicio, Integer anoFim);

    @Query("SELECT t FROM Tecnica t join Docente d join Programa p where p.id = :programaId and t.ano >= :anoInicio and t.ano <= :anoFim")
    List<Tecnica> obterTecnicasPPG(Integer programaId, Integer anoInicio, Integer anoFim);

    @Query("SELECT o FROM Orientacao o join Tecnica t where t.id = :tecnicaId and o.ano >= :anoInicio and o.ano <= :anoFim")
    List<Orientacao> obterOrientacoesTecnica(Integer tecnicaId, Integer anoInicio, Integer anoFim);
=======
   
    boolean existsById(Integer idTecnica);
    
    // Obter todas as técnicas de um docente em um período
    @Query("SELECT t FROM Tecnica t " +
            "JOIN Docente d " +
            "WHERE d.id = :idDocente " +
            "AND t.ano >= :anoInicio " +
            "AND t.ano <= :anoFim")
    Optional<List<Tecnica>> obterTecnicasDocentePorPeriodo(Integer idDocente, Integer anoInicio, Integer anoFim);

    // Obter todas as técnicas de uma orientação em um período
    @Query("SELECT t FROM Tecnica t " +
            "JOIN Orientacao o " +
            "WHERE o.id = :idOrientacao " +
            "AND t.ano >= :anoInicio " +
            "AND t.ano <= :anoFim")
    Optional<List<Tecnica>> obterTecnicasOrientacaoPorPeriodo(Integer idOrientacao, Integer anoInicio,
            Integer anoFim);

    @Query("SELECT t FROM Tecnica t " +
            "JOIN Docente d " +
            "JOIN Programa p " +
            "WHERE p.id = :idPrograma")
    Optional<List<Tecnica>> obterTecnicasPPG(Integer idPrograma);

    @Query("SELECT t FROM Tecnica t " +
            "JOIN Docente d " +
            "JOIN Programa p " +
            "WHERE p.id = :idPrograma " +
            "AND t.ano >= :anoInicio " +
            "AND t.ano <= :anoFim")
    Optional<List<Tecnica>> obterTecnicasPPGPorPeriodo(Integer idPrograma, Integer anoInicio, Integer anoFim);
>>>>>>> 74f1f51547bfc7d68a1532c4e90c26e8823c6e6a

}
