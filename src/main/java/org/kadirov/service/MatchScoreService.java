package org.kadirov.service;

import java.util.Optional;

public interface MatchScoreService {
    void updateScore(PlayerScore wonPointPlayer, PlayerScore otherPlayer);
    Optional<PlayerScore> getWinner(PlayerScore wonPlayer, PlayerScore otherPlayer);
}
