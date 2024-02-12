package fr.ancyracademy.esportclash.modules.player.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.shared.IdResponse;
import fr.ancyracademy.esportclash.shared.id.IdProvider;

public class CreatePlayerCommandHandler implements Command.Handler<CreatePlayerCommand, IdResponse> {
  private final IdProvider idProvider;

  private final PlayerRepository playerRepository;

  public CreatePlayerCommandHandler(IdProvider idProvider, PlayerRepository playerRepository) {
    this.idProvider = idProvider;
    this.playerRepository = playerRepository;
  }

  @Override
  public IdResponse handle(CreatePlayerCommand request) {
    Player player = new Player(idProvider.generate(), request.getName(), request.getMainRole());
    playerRepository.save(player);

    return new IdResponse(player.getId());
  }
}
