package fr.ancyracademy.esportclash.modules.player.queries;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.player.viewmodel.PlayerViewModel;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;

public class GetPlayerByIdQueryHandler implements Command.Handler<GetPlayerByIdQuery, PlayerViewModel> {

  private final PlayerRepository playerRepository;

  public GetPlayerByIdQueryHandler(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public PlayerViewModel handle(GetPlayerByIdQuery request) {
    Player player = playerRepository.findById(request.getId()).orElseThrow(
        () -> new NotFoundException("Player not found")
    );

    return new PlayerViewModel(player);
  }
}
