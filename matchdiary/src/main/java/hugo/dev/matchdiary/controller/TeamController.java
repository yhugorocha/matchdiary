package hugo.dev.matchdiary.controller;

import hugo.dev.matchdiary.dto.team.TeamDTO;
import hugo.dev.matchdiary.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<TeamDTO>> getPagination(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size){
        return ResponseEntity.ok(teamService.findAll(page, size));
    }

}
