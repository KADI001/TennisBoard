package org.kadirov.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.kadirov.model.PlayerScore;

@Entity
@Table(name = "Players_score")
public class PlayerScoreEntity implements PlayerScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "player_id", nullable = false)
    private int playerId;

    @Column(name = "points")
    @ColumnDefault("0")
    private int points;

    @Column(name = "games")
    @ColumnDefault("0")
    private int games;

    @Column(name = "sets")
    @ColumnDefault("0")
    private int sets;

    public PlayerScoreEntity() {
    }

    public PlayerScoreEntity(int id, int playerId, int points, int games, int sets) {
        this.id = id;
        this.playerId = playerId;
        this.points = points;
        this.games = games;
        this.sets = sets;
    }

    public PlayerScoreEntity(int playerId, int points, int games, int sets) {
        this.playerId = playerId;
        this.points = points;
        this.games = games;
        this.sets = sets;
    }

    public PlayerScoreEntity(int playerId) {
        this.playerId = playerId;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getPlayerId() {
        return playerId;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getGames() {
        return games;
    }

    @Override
    public int getSets() {
        return sets;
    }

    @Override
    public void addPoints(int points){
        this.points+=points;
    }
    @Override
    public void addGame(){
        games++;
    }
    @Override
    public void addSet(){
        sets++;
    }
    @Override
    public void resetPoints() {
        points = 0;
    }
    @Override
    public void resetGames() {
        games = 0;
    }

    @Override
    public void resetSets() {
        sets = 0;
    }
}
