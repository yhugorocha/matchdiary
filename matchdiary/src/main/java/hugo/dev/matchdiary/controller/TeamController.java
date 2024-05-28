package hugo.dev.matchdiary.controller;

import hugo.dev.matchdiary.model.Team;
import hugo.dev.matchdiary.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllteams(){
        List<Team> listTeams = teamService.findAll();
        return ResponseEntity.ok(listTeams);
    }

    @GetMapping("{id}")
    public ResponseEntity<Team> getFindById(@PathVariable(value = "id") Long id){
        Team team = teamService.findById(id).orElseThrow();

        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Team>> getPagination(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size){

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(teamService.findAll(pageable));
    }

}
