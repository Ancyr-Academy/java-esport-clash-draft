package fr.ancyracademy.esportclash.modules.player.usecases;

import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.player.viewmodel.PlayerViewModel;
import fr.ancyracademy.esportclash.shared.UseCase;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;

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
