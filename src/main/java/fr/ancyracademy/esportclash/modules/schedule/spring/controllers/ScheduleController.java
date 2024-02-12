package fr.ancyracademy.esportclash.modules.schedule.spring.controllers;

import an.awesome.pipelinr.Pipeline;
import fr.ancyracademy.esportclash.modules.schedule.commands.CancelMatchCommand;
import fr.ancyracademy.esportclash.modules.schedule.commands.OrganizeMatchCommand;
import fr.ancyracademy.esportclash.modules.schedule.spring.dto.OrganizeMatchDTO;
import fr.ancyracademy.esportclash.shared.IdResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {
  private final Pipeline pipeline;

  public ScheduleController(
      Pipeline pipeline
  ) {
    this.pipeline = pipeline;
  }


  @PostMapping("schedule/organize")
  public ResponseEntity<IdResponse> organizeMatch(
      @Valid @RequestBody OrganizeMatchDTO dto
  ) {
    var result = pipeline.send(
        new OrganizeMatchCommand(
            dto.getDate(),
            dto.getFirstTeamId(),
            dto.getSecondTeamId(),
            dto.getMoment()
        )
    );

    return new ResponseEntity<>(new IdResponse(result.getId()), HttpStatus.OK);
  }

  @DeleteMapping("matches/{id}")
  public ResponseEntity<Void> cancelMatch(
      @PathVariable("id") String id
  ) {
    pipeline.send(
        new CancelMatchCommand(id)
    );

    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }
}
