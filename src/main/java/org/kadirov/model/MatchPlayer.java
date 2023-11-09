package org.kadirov.model;

import org.kadirov.entity.PlayerEntity;

public class MatchPlayer {

    private PlayerEntity playerEntity;

    private int score;
    private int games;
    private int sets;

    public MatchPlayer(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }


    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public String getName(){
        return playerEntity.getName();
    }

    public int getId(){
        return playerEntity.getId();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}
