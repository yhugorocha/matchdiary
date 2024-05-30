package hugo.dev.matchdiary.dto.match;

import hugo.dev.matchdiary.dto.team.TeamDTO;
import hugo.dev.matchdiary.model.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequestDTO {

    private Long id;
    private Date date;
    private Long teamOne;
    private Integer scoreTeamOne;
    private Long teamTwo;
    private Integer scoreTeamTwo;
    private Long team_supported;

}
