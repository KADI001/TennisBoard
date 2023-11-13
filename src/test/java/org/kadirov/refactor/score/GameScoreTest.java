package org.kadirov.refactor.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kadirov.refactor.score.GameScore;
import org.kadirov.refactor.score.Player;
import org.kadirov.refactor.score.ScoreState;

import static org.assertj.core.api.Assertions.assertThat;

class GameScoreTest {

    private GameScore underTest;

    @BeforeEach
    void setUp() {
        underTest = new GameScore();
    }

    @Test
    void getZeroScore() {
        //Given
        int expected = 0;

        //When
        int actual = underTest.getZeroScore();

        //Then
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void WillWinPlayerOne_IfPlayerOneScore6AndPlayerTwoScoreLessThan5_WhenWinPoint() {
        //Given
        Player player1 = Player.ONE;

        //Make score 5:0, to win one game we need get 4 points => win 5 games? get 5 * 4 points = 20 points
        for (int i = 0; i < 20; i++) {
            underTest.winPoint(player1);
        }

        //When
        //Make score 6:0
        for (int i = 0; i < 4; i++) {
            underTest.winPoint(player1);
        }

        //Then
        assertThat(underTest.state).isEqualTo(ScoreState.FIRST_PLAYER_WON);
    }

    @Test
    void WillNotWinPlayerOne_IfPlayerOneScore6AndPlayerTwoScore5_WhenWinPoint() {
        //Given
        Player player1 = Player.ONE;
        Player player2 = Player.TWO;

        //Make score 5:5, to win one game we need get 4 points => win 5 games? get 5 * 4 points = 20 points
        for (int i = 0; i < 20; i++) {
            underTest.winPoint(player1);
        }

        for (int i = 0; i < 20; i++) {
            underTest.winPoint(player2);
        }

        //When
        //Make score 6:5
        for (int i = 0; i < 4; i++) {
            underTest.winPoint(player1);
        }

        //Then
        assertThat(underTest.state).isEqualTo(ScoreState.ONGOING);
    }

    @Test
    void WillWinPlayerOne_IfPlayerOneScore7AndPlayerTwoScore5_WhenWinPoint() {
        //Given
        Player player1 = Player.ONE;
        Player player2 = Player.TWO;

        //Make score 5:5, to win one game we need get 4 points => win 5 games? get 5 * 4 points = 20 points
        for (int i = 0; i < 20; i++) {
            underTest.winPoint(player1);
        }

        for (int i = 0; i < 20; i++) {
            underTest.winPoint(player2);
        }

        //Make score 6:5
        for (int i = 0; i < 4; i++) {
            underTest.winPoint(player1);
        }

        //When
        //Make score 7:5
        for (int i = 0; i < 4; i++) {
            underTest.winPoint(player1);
        }

        //Then
        assertThat(underTest.state).isEqualTo(ScoreState.FIRST_PLAYER_WON);
    }
}