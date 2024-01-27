package fr.ancyracademy.esportclash.modules.team.usecases;

import fr.ancyracademy.esportclash.modules.player.model.Role;

public record AddPlayerToTeamInput(String teamId, String playerId, Role role) {
}
