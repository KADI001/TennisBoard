package org.kadirov.model;

import org.kadirov.entity.PlayerEntity;

import java.util.UUID;

public class ActiveMatch {
    private UUID id;

    private final MatchPlayer firstPlayer;

    private final MatchPlayer secondPlayer;

    private PlayerEntity winner;

    public ActiveMatch(MatchPlayer firstPlayer, MatchPlayer secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MatchPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public MatchPlayer getSecondPlayer() {
        return secondPlayer;
    }

    public PlayerEntity getWinner() {
        return winner;
    }

    public void setWinner(PlayerEntity winner) {
        this.winner = winner;
    }
}
