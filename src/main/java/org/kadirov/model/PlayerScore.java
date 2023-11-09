package org.kadirov.model;

public interface PlayerScore {

    int getPlayerId();

    int getPoints();
    int getGames();
    int getSets();

    void addPoints(int points);
    void addGame();
    void addSet();

    void resetPoints();
    void resetGames();
    void resetSets();
}
