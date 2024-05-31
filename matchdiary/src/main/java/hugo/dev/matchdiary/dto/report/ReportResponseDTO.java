package hugo.dev.matchdiary.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {

    private Integer matchesQuantity;
    private Integer winsQuantity;
    private Double winPercentage;
    private Integer daysWithoutMatch;
}
