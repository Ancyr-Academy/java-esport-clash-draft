package fr.ancyracademy.javaesportclash.modules.player.usecases;

import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.javaesportclash.shared.UseCase;
import org.springframework.stereotype.Service;

@Service
public class ChangePlayerMainRoleUseCase implements UseCase<ChangePlayerMainRoleInput, Void> {

  private final PlayerRepository playerRepository;

  public ChangePlayerMainRoleUseCase(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Void execute(ChangePlayerMainRoleInput request) {
    Player player = playerRepository.findById(request.getId()).orElseThrow();
    player.setMainRole(request.getMainRole());
    playerRepository.save(player);

    return null;
  }
}
