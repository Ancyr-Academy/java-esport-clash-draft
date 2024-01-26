package fr.ancyracademy.javaesportclash.modules.player.usecases;

import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.model.Role;
import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.javaesportclash.shared.IdResponse;
import fr.ancyracademy.javaesportclash.shared.UseCase;
import fr.ancyracademy.javaesportclash.shared.id.IdProvider;

public class CreatePlayerUseCase implements UseCase<CreatePlayerInput, IdResponse> {
  private final IdProvider idProvider;

  private final PlayerRepository playerRepository;

  public CreatePlayerUseCase(IdProvider idProvider, PlayerRepository playerRepository) {
    this.idProvider = idProvider;
    this.playerRepository = playerRepository;
  }

  @Override
  public IdResponse execute(CreatePlayerInput request) {
    Player player = new Player(idProvider.generate(), request.getName(), Role.fromString(request.getMainRole()));
    playerRepository.save(player);

    return new IdResponse(player.getId());
  }
}
