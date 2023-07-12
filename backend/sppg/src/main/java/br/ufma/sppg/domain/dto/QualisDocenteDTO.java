package br.ufma.sppg.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class QualisDocenteDTO {
    private String docente;
    private Integer docenteId;
    private List<QualisStatsDTO> stats;
}
