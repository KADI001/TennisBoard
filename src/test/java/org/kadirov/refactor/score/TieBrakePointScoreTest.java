package org.kadirov.refactor.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kadirov.refactor.score.Player;
import org.kadirov.refactor.score.ScoreState;
import org.kadirov.refactor.score.TieBrakePointScore;

import static org.assertj.core.api.Assertions.assertThat;

class TieBrakePointScoreTest {

    private TieBrakePointScore underTest;

    @BeforeEach
    void setUp() {
        underTest = new TieBrakePointScore();
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
    void WillWinPlayerOne_IfPlayerOneScore7AndPlayerTwoScoreLessThan6_WhenWinPoint() {
        //Given
        Player player1 = Player.ONE;

        //Make 6:0
        for (int i = 0; i < 6; i++) {
            underTest.winPoint(player1);
        }

        //When
        //Make 7:0
        underTest.winPoint(player1);

        //Then
        assertThat(underTest.state).isEqualTo(ScoreState.FIRST_PLAYER_WON);
    }

    @Test
    void WillNotWinPlayerOne_IfPlayerOneScore7AndPlayerTwoScore6_WhenWinPoint() {
        //Given
        Player player1 = Player.ONE;
        Player player2 = Player.TWO;

        //Make 6:6
        for (int i = 0; i < 6; i++) {
            underTest.winPoint(player1);
            underTest.winPoint(player2);
        }

        //When
        //Make 7:6
        underTest.winPoint(player1);

        //Then
        assertThat(underTest.state).isEqualTo(ScoreState.ONGOING);
    }

    @Test
    void WillWinPlayerOne_IfPlayerOneScore8AndPlayerTwoScore6_WhenWinPoint() {
        //Given
        Player player1 = Player.ONE;
        Player player2 = Player.TWO;

        //Make 6:6
        for (int i = 0; i < 6; i++) {
            underTest.winPoint(player1);
            underTest.winPoint(player2);
        }

        //Make 7:6
        underTest.winPoint(player1);

        //When
        //Make 8:6
        underTest.winPoint(player1);

        //Then
        assertThat(underTest.state).isEqualTo(ScoreState.FIRST_PLAYER_WON);
    }
}