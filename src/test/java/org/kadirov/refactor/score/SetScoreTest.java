package org.kadirov.refactor.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kadirov.refactor.score.Player;
import org.kadirov.refactor.score.ScoreState;
import org.kadirov.refactor.score.SetScore;

import static org.assertj.core.api.Assertions.assertThat;

class SetScoreTest {

    private SetScore underTest;

    @BeforeEach
    void setUp() {
        underTest = new SetScore();
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
    void WillWinPlayerOne_IfPlayerOneScore2AndPlayerTwoLessThan2_WhenWinPoint() {
        //Given
        Player player1 = Player.ONE;

        //Make score 1:0, to win one set we need win 6 games usual => win 5 sets? get 6 * 4 points = 24 points
        for (int i = 0; i < 24; i++) {
            underTest.winPoint(player1);
        }

        //When
        //Make score 2:0
        for (int i = 0; i < 24; i++) {
            underTest.winPoint(player1);
        }

        //Then
        assertThat(underTest.state).isEqualTo(ScoreState.FIRST_PLAYER_WON);
    }
}