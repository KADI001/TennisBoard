package org.kadirov.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "Active_matches")
public class ActiveMatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("random_uuid()")
    @Column(name = "id")
    private String id;

    @OneToOne
    @JoinColumn(name = "first_player_score_id", nullable = false)
    private PlayerScoreEntity firstPlayerScore;

    @OneToOne
    @JoinColumn(name = "second_player_score_id", nullable = false)
    private PlayerScoreEntity secondPlayerScore;

    public ActiveMatchEntity() {
    }

    public ActiveMatchEntity(String id, PlayerScoreEntity firstPlayerScore, PlayerScoreEntity secondPlayerScore) {
        this.id = id;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    public ActiveMatchEntity(PlayerScoreEntity firstPlayerScore, PlayerScoreEntity secondPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    public String getId() {
        return id;
    }
    public PlayerScoreEntity getFirstPlayerScore() {
        return firstPlayerScore;
    }
    public PlayerScoreEntity getSecondPlayerScore() {
        return secondPlayerScore;
    }
}
