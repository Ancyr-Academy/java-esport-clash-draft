package fr.ancyracademy.esportclash.modules.player.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;

public class ChangePlayerMainRoleCommandHandler implements Command.Handler<ChangePlayerMainRoleCommand, Void> {

  private final PlayerRepository playerRepository;

  public ChangePlayerMainRoleCommandHandler(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Void handle(ChangePlayerMainRoleCommand request) {
    Player player = playerRepository.findById(request.getId()).orElseThrow(
        () -> new NotFoundException("Player not found"));

    player.setMainRole(request.getMainRole());
    playerRepository.save(player);

    return null;
  }
}
