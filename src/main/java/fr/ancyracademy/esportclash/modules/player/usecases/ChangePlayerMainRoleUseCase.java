package fr.ancyracademy.esportclash.modules.player.usecases;

import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.shared.UseCase;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ChangePlayerMainRoleUseCase implements UseCase<ChangePlayerMainRoleInput, Void> {

  private final PlayerRepository playerRepository;

  public ChangePlayerMainRoleUseCase(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Void execute(ChangePlayerMainRoleInput request) {
    Player player = playerRepository.findById(request.getId()).orElseThrow(
        () -> new NotFoundException("Player not found"));

    player.setMainRole(request.getMainRole());
    playerRepository.save(player);

    return null;
  }
}
