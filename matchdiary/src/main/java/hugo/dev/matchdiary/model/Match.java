package hugo.dev.matchdiary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    private Date date;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "team_one_id")
    private Team teamOne;


    @Column(name = "score_team_one")
    private Integer scoreTeamOne;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "team_two_id")
    private Team teamTwo;

    @Column(name = "score_team_two")
    private Integer scoreTeamTwo;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "team_supported_id")
    private Team team_supported;


}
