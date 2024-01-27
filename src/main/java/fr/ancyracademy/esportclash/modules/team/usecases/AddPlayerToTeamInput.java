package fr.ancyracademy.esportclash.modules.team.usecases;

import fr.ancyracademy.esportclash.modules.player.model.Role;

import java.util.UUID;

public record AddPlayerToTeamInput(UUID teamId, UUID playerId, Role role) {
}
