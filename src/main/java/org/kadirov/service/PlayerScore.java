package org.kadirov.service;

import org.kadirov.entity.PlayerEntity;

public interface PlayerScore {

    PlayerEntity getPlayer();

    int getPoints();
    int getGames();
    int getSets();

    void addPoints(int points);
    void addGame();
    void addSet();

    void resetPoints();
    void resetGames();
}
