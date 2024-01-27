package fr.ancyracademy.esportclash.modules.player.usecases;

import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.shared.IdResponse;
import fr.ancyracademy.esportclash.shared.UseCase;
import fr.ancyracademy.esportclash.shared.id.IdProvider;

public class CreatePlayerUseCase implements UseCase<CreatePlayerInput, IdResponse> {
  private final IdProvider idProvider;

  private final PlayerRepository playerRepository;

  public CreatePlayerUseCase(IdProvider idProvider, PlayerRepository playerRepository) {
    this.idProvider = idProvider;
    this.playerRepository = playerRepository;
  }

  @Override
  public IdResponse execute(CreatePlayerInput request) {
    Player player = new Player(idProvider.generate(), request.getName(), request.getMainRole());
    playerRepository.save(player);

    return new IdResponse(player.getId());
  }
}
