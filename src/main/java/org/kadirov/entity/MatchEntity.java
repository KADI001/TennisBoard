package org.kadirov.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "first_player_id", nullable = false)
    private PlayerEntity firstPlayer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "second_player_id", nullable = false)
    private PlayerEntity secondPlayer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "winner_player_id", nullable = false)
    private PlayerEntity winner;

    public MatchEntity() {
    }

    public MatchEntity(int id, PlayerEntity firstPlayer, PlayerEntity secondPlayer, PlayerEntity winner) {
        this.id = id;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.winner = winner;
    }

    public MatchEntity(PlayerEntity firstPlayer, PlayerEntity secondPlayer, PlayerEntity winner) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.winner = winner;
    }

    public MatchEntity(PlayerEntity firstPlayer, PlayerEntity secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerEntity getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(PlayerEntity firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public PlayerEntity getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(PlayerEntity secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public PlayerEntity getWinner() {
        return winner;
    }

    public void setWinner(PlayerEntity winner) {
        this.winner = winner;
    }
}
