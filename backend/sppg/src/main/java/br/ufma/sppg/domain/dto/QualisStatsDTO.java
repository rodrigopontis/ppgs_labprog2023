package br.ufma.sppg.domain.dto;

import br.ufma.sppg.domain.enums.QualisEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class QualisStatsDTO {
    private QualisEnum type;
    private Integer qtd;
}
