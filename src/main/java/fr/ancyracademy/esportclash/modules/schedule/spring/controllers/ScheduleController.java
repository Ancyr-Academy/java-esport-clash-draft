package fr.ancyracademy.esportclash.modules.schedule.spring.controllers;

import fr.ancyracademy.esportclash.modules.schedule.spring.dto.OrganizeMatchDTO;
import fr.ancyracademy.esportclash.modules.schedule.usecases.CancelMatchInput;
import fr.ancyracademy.esportclash.modules.schedule.usecases.CancelMatchUseCase;
import fr.ancyracademy.esportclash.modules.schedule.usecases.OrganizeMatchInput;
import fr.ancyracademy.esportclash.modules.schedule.usecases.OrganizeMatchUseCase;
import fr.ancyracademy.esportclash.shared.IdResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {
  private final OrganizeMatchUseCase organizeMatchUseCase;

  private final CancelMatchUseCase cancelMatchUseCase;

  public ScheduleController(
      OrganizeMatchUseCase organizeMatchUseCase,
      CancelMatchUseCase cancelMatchUseCase
  ) {
    this.organizeMatchUseCase = organizeMatchUseCase;
    this.cancelMatchUseCase = cancelMatchUseCase;
  }

  @PostMapping("schedule/organize")
  public ResponseEntity<IdResponse> organizeMatch(
      @Valid @RequestBody OrganizeMatchDTO dto
  ) {
    var result = organizeMatchUseCase.execute(
        new OrganizeMatchInput(
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
    cancelMatchUseCase.execute(
        new CancelMatchInput(id)
    );
    
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }
}
