package org.kadirov.model;

import org.kadirov.entity.PlayerEntity;

public class Player implements PlayerScore {

    private final PlayerEntity entity;

    private int points;
    private int games;
    private int sets;

    public Player(PlayerEntity entity) {
        this.entity = entity;
    }

    public Player(PlayerEntity entity, int points, int games, int sets) {
        this.entity = entity;
        this.points = points;
        this.games = games;
        this.sets = sets;
    }

    public PlayerEntity getEntity() {
        return entity;
    }

    @Override
    public int getPlayerId() {
        return entity.getId();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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

    public void addPoints(int points){
        setPoints(getPoints() + points);
    }
    public void addGame(){
        setGames(getGames() + 1);
    }
    public void addSet(){
        setSets(getSets() + 1);
    }

    public void resetPoints() {
        setPoints(0);
    }
    public void resetGames() {
        setGames(0);
    }
    public void resetSets() {
        setSets(0);
    }
}
