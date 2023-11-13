package org.kadirov.refactor.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kadirov.refactor.score.Player;
import org.kadirov.refactor.score.Point;
import org.kadirov.refactor.score.RegularPointScore;
import org.kadirov.refactor.score.ScoreState;

import static org.assertj.core.api.Assertions.assertThat;

class RegularPointScoreTest {

    private RegularPointScore underTest;

    @BeforeEach
    void setUp() {
        underTest = new RegularPointScore();
    }

    @Test
    void getZeroScore() {
        //Given
        Point expected = Point.ZERO;

        //When
        Point actual = underTest.getZeroScore();

        //Then
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void WillWinPlayerOne_IfPlayerOneScoreMoreThan40AndPlayerTwoScoreLessThan40_WhenWinPoint() {
        //Given
        Player player = Player.ONE;

        //When
        for (int i = 0; i < 4; i++) {
            underTest.winPoint(player);
        }

        //Then
        assertThat(underTest.state).isEqualTo(ScoreState.FIRST_PLAYER_WON);
    }

    @Test
    void WillWinPlayerOne_IfPlayerOneScoreMoreThanADVENTAGEAndPlayerTwoScore40_WhenWinPoint() {
        //Given
        Player player1 = Player.ONE;
        Player player2 = Player.TWO;

        //When
        for (int i = 0; i < 4; i++) {
            underTest.winPoint(player1);
            underTest.winPoint(player2);
        }

        for (int i = 0; i < 2; i++) {
            underTest.winPoint(player1);
        }

        //Then
        assertThat(underTest.state).isEqualTo(ScoreState.FIRST_PLAYER_WON);
    }

    @Test
    void WillOngoing_IfPlayerTwoScoreBecomeADVENTAGE_WhilePlayerOneScoreAlreadyADVANTAGE_WhenWinPoint() {
        //Given
        Player player1 = Player.ONE;
        Player player2 = Player.TWO;

        // Make score 40:40
        for (int i = 0; i < 3; i++) {
            underTest.winPoint(player1);
            underTest.winPoint(player2);
        }

        // Make score AD:40
        underTest.winPoint(player1);

        //When
        // Make score 40:40 again
        underTest.winPoint(player2);

        //Then
        assertThat(underTest.getPlayerScore(player1)).isEqualTo(Point.FORTY);
        assertThat(underTest.getPlayerScore(player2)).isEqualTo(Point.FORTY);
        assertThat(underTest.state).isEqualTo(ScoreState.ONGOING);
    }
}