package hugo.dev.matchdiary.dto.team;

import hugo.dev.matchdiary.model.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String state;
    @NotBlank(message = "Not null")
    @NotEmpty(message = "Not empty")
    @NotNull(message = "Not null")
    private String photo_url;

    public TeamDTO (Team team){
        this.id = team.getId();
        this.name = team.getName();
        this.state = team.getState();
        this.photo_url = team.getPhoto_url();
    }
}
