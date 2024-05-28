package hugo.dev.matchdiary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "date")
    @NotEmpty
    private Date date;

    @JoinColumn(name = "team_one_id")
    @NotEmpty
    private Team teamOne;

    @ManyToOne
    @JsonManagedReference
    @Column(name = "score_team_one")
    @NotEmpty
    private Integer scoreTeamOne;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "team_two_id")
    @NotEmpty
    private Team teamTwo;

    @Column(name = "score_team_two")
    @NotEmpty
    private Integer scoreTeamTwo;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "team_supported_id")
    @NotEmpty
    private Team team_supported;


}
