package org.kadirov.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.kadirov.service.PlayerScore;

@Entity
@Table(name = "Players_scores")
public class PlayerScoreEntity implements PlayerScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;

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

    public PlayerScoreEntity(int id, PlayerEntity player, int points, int games, int sets) {
        this.id = id;
        this.player = player;
        this.points = points;
        this.games = games;
        this.sets = sets;
    }

    public PlayerScoreEntity(PlayerEntity player, int points, int games, int sets) {
        this.player = player;
        this.points = points;
        this.games = games;
        this.sets = sets;
    }

    public PlayerScoreEntity(PlayerEntity player) {
        this.player = player;
    }

    public int getId() {
        return id;
    }

    @Override
    public PlayerEntity getPlayer() {
        return player;
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
}
