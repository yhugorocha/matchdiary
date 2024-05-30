package hugo.dev.matchdiary.controller;

import hugo.dev.matchdiary.dto.match.MatchRequestDTO;
import hugo.dev.matchdiary.dto.match.MatchResponseDTO;
import hugo.dev.matchdiary.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<MatchResponseDTO>> getAllMatchs(){
        return ResponseEntity.ok(matchService.getAllMatchs());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MatchResponseDTO> getMatchById(@PathVariable Long id){
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<MatchResponseDTO>> getAllForPagination(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size){
        return ResponseEntity.ok(matchService.findAll(page, size));
    }

    @PostMapping
    public ResponseEntity<MatchResponseDTO> save(@RequestBody MatchRequestDTO matchRequestDTO){
        return new ResponseEntity<>(matchService.save(matchRequestDTO), HttpStatus.CREATED);
    }

}
