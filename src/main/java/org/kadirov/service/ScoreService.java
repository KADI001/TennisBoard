package org.kadirov.service;

import org.kadirov.entity.PlayerScoreEntity;
import org.kadirov.model.Player;
import org.kadirov.model.PlayerScore;

import java.util.Optional;

public interface ScoreService {
    void updateScore(PlayerScore wonPlayer, PlayerScore otherPlayer);
    Optional<PlayerScore> getWinner(PlayerScore wonPlayer, PlayerScore otherPlayer);
}
