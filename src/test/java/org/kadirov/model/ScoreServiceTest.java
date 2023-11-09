package org.kadirov.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kadirov.service.ScoreService;
import org.kadirov.service.ScoreServiceImpl;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScoreServiceTest {

    private ScoreService underTest;

    private Player firstPlayer;
    private Player secondPlayer;

    @BeforeEach
    void setUp() {
        firstPlayer = mock(Player.class);
        secondPlayer = mock(Player.class);
        underTest = new ScoreServiceImpl();
    }

    @Test
    void WillWinOneGame_IfFirstPlayerWinPointTo40_WhileSecondPlayerHasLessThan15_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(40);
        given(secondPlayer.getPoints()).willReturn(15);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer).resetPoints();
        verify(secondPlayer).resetPoints();
        verify(firstPlayer).addGame();
    }

    @Test
    void WillWinOneGame_IfFirstPlayerWinPointTo50_WhileSecondPlayerHasLessThan30_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(50);
        given(secondPlayer.getPoints()).willReturn(30);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer).resetPoints();
        verify(secondPlayer).resetPoints();
        verify(firstPlayer).addGame();
    }


    @Test
    void WillNotWinOneGame_IfFirstPlayerWinPointTo40_WhileSecondPlayerHas30_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(40);
        given(secondPlayer.getPoints()).willReturn(30);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer, never()).resetPoints();
        verify(secondPlayer, never()).resetPoints();
        verify(firstPlayer, never()).addGame();
    }

    @Test
    void WillWinOneGame_IfFirstPlayerWinPointTo60_WhileSecondPlayerHas40_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(60);
        given(secondPlayer.getPoints()).willReturn(40);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer).resetPoints();
        verify(secondPlayer).resetPoints();
        verify(firstPlayer).addGame();
    }

    @Test
    void WillNotWinOneGame_IfFirstPlayerWinPointTo50_WhileSecondPlayerHas40_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(50);
        given(secondPlayer.getPoints()).willReturn(40);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer, never()).resetPoints();
        verify(secondPlayer, never()).resetPoints();
        verify(firstPlayer, never()).addGame();
    }

    @Test
    void WillNotWinOneGame_IfFirstPlayerWinPointTo30_WhileSecondPlayerHas30_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(30);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer, never()).resetPoints();
        verify(secondPlayer, never()).resetPoints();
        verify(firstPlayer, never()).addGame();
    }

    @Test
    void WillWinOneGameInTieBreak_IfFirstPlayerWinPointTo7_WhileSecondPlayerHasLessThan7_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(7);
        given(firstPlayer.getGames()).willReturn(6);
        given(secondPlayer.getGames()).willReturn(6);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer).resetPoints();
        verify(secondPlayer).resetPoints();
        verify(firstPlayer).resetGames();
        verify(secondPlayer).resetGames();
        verify(firstPlayer).addSet();
    }

    @Test
    void WillNotWinOneGameInTieBreak_IfFirstPlayerWinPointToLessThan7_WhileSecondPlayerHasLessThan7_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(6);
        given(firstPlayer.getGames()).willReturn(6);
        given(secondPlayer.getGames()).willReturn(6);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer, never()).resetPoints();
        verify(secondPlayer, never()).resetPoints();
        verify(firstPlayer, never()).addGame();
    }

    @Test
    void WillWinOneSet_IfFirstPlayerWinGameTo6_WhileSecondPlayerHas4_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(40);
        given(secondPlayer.getPoints()).willReturn(15);
        given(firstPlayer.getGames()).willReturn(6);
        given(secondPlayer.getGames()).willReturn(4);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer).resetPoints();
        verify(secondPlayer).resetPoints();
        verify(firstPlayer).addGame();
        verify(firstPlayer).resetGames();
        verify(secondPlayer).resetGames();
        verify(firstPlayer).addSet();
    }

    @Test
    void WillWinOneSet_IfFirstPlayerWinGameTo7_WhileSecondPlayerHas5_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(40);
        given(secondPlayer.getPoints()).willReturn(15);
        given(firstPlayer.getGames()).willReturn(7);
        given(secondPlayer.getGames()).willReturn(5);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer).resetPoints();
        verify(secondPlayer).resetPoints();
        verify(firstPlayer).addGame();
        verify(firstPlayer).resetGames();
        verify(secondPlayer).resetGames();
        verify(firstPlayer).addSet();
    }

    @Test
    void WillNotWinOneSet_IfFirstPlayerWinGameTo6_WhileSecondPlayerHas5_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(40);
        given(secondPlayer.getPoints()).willReturn(15);
        given(firstPlayer.getGames()).willReturn(6);
        given(secondPlayer.getGames()).willReturn(5);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer).resetPoints();
        verify(secondPlayer).resetPoints();
        verify(firstPlayer).addGame();
        verify(firstPlayer, never()).resetGames();
        verify(secondPlayer, never()).resetGames();
        verify(firstPlayer, never()).addSet();
    }

    @Test
    void WillNotWinOneSet_IfFirstPlayerWinGameTo7_WhileSecondPlayerHas6_WhenUpdateScore() {
        //Given
        given(firstPlayer.getPoints()).willReturn(40);
        given(secondPlayer.getPoints()).willReturn(15);
        given(firstPlayer.getGames()).willReturn(7);

        //When
        underTest.updateScore(firstPlayer, secondPlayer);

        //Then
        verify(firstPlayer).resetPoints();
        verify(secondPlayer).resetPoints();
        verify(firstPlayer).addGame();
        verify(firstPlayer, never()).resetGames();
        verify(secondPlayer, never()).resetGames();
        verify(firstPlayer, never()).addSet();
    }

    @Test
    void WillReturnWinner_IfFirstPlayerWinSetTo2_WhileSecondPlayerHasLessThan2_WhenGetWinner() {
        //Given
        given(firstPlayer.getSets()).willReturn(2);
        given(secondPlayer.getSets()).willReturn(1);

        //When
        Optional<PlayerScore> winner = underTest.getWinner(firstPlayer, secondPlayer);

        //Then
        assertThat(winner).isPresent();
    }

    @Test
    void WillNotReturnWinner_IfFirstPlayerWinSetToLessThan2_WhileSecondPlayerHasLessThan2_WhenGetWinner() {
        //Given
        given(firstPlayer.getSets()).willReturn(1);
        given(secondPlayer.getSets()).willReturn(1);

        //When
        Optional<PlayerScore> winner = underTest.getWinner(firstPlayer, secondPlayer);

        //Then
        assertThat(winner).isEmpty();
    }
}