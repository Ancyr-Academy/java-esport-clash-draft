package fr.ancyracademy.javaesportclash.modules.player.usecases;

import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.javaesportclash.modules.player.viewmodel.PlayerViewModel;
import fr.ancyracademy.javaesportclash.shared.UseCase;
import fr.ancyracademy.javaesportclash.shared.exceptions.NotFoundException;

public class GetPlayerByIdUseCase implements UseCase<GetPlayerByIdInput, PlayerViewModel> {

  private final PlayerRepository playerRepository;

  public GetPlayerByIdUseCase(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public PlayerViewModel execute(GetPlayerByIdInput request) {
    Player player = playerRepository.findById(request.getId()).orElseThrow(
        () -> new NotFoundException("Player not found")
    );
    
    return new PlayerViewModel(player);
  }
}
