package hugo.dev.matchdiary.controller;

import hugo.dev.matchdiary.dto.team.TeamDTO;
import hugo.dev.matchdiary.service.TeamService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/all")
    public ResponseEntity<List<TeamDTO>> getAllteams(){
        return ResponseEntity.ok(teamService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TeamDTO> getFindById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(teamService.findById(id));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<TeamDTO>> getAllForPagination(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size){
        return ResponseEntity.ok(teamService.findAll(page, size));
    }

    @PostMapping
    public ResponseEntity<TeamDTO> save(@Valid @RequestBody TeamDTO teamDTO){
        return new ResponseEntity<>(teamService.save(teamDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TeamDTO> update(@Valid @RequestBody  TeamDTO teamDTO){
        return new ResponseEntity<>(teamService.update(teamDTO),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
