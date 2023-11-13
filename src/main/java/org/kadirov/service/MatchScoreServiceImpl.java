package org.kadirov.service;

import java.util.Optional;

public class MatchScoreServiceImpl implements MatchScoreService {
    
    public static final int TIE_BREAK_COUNT_GAMES = 6;
    public static final int GAME_WIN_COUNT_POINTS = 40;
    public static final int EQUALS_COUNT_INCREASE_POINTS = 10;
    public static final int EQUALS_DOUBLE_COUNT_INCREASE_POINTS = 2 * EQUALS_COUNT_INCREASE_POINTS;
    public static final int DEFAULT_COUNT_INCREASE_POINTS = 15;
    public static final int TIE_BREAK_COUNT_INCREASE_POINTS = 1;
    public static final int FIRST_POINT_COUNT_POINTS = 15;
    public static final int WIN_TIE_BREAK_COUNT_POINTS = 7;
    public static final int SECOND_POINT_COUNT_POINTS = 30;
    public static final int WON_SET_COUNT_GAMES = 6;
    public static final int SETS_WIN_COUNT_POINTS = 2;

    @Override
    public void updateScore(PlayerScore wonPointPlayer, PlayerScore otherPlayer) {
        if (isTieBreak(wonPointPlayer, otherPlayer)) {
            onWonPointInTieBreak(wonPointPlayer);

            if(isTieBreakWinner(wonPointPlayer, otherPlayer))
                onWonTieBreak(wonPointPlayer, otherPlayer);
        } else {
            onWonPoint(wonPointPlayer);

            if(isGameWinner(wonPointPlayer, otherPlayer)){
                onWonGame(wonPointPlayer, otherPlayer);

                if (isSetWinner(wonPointPlayer, otherPlayer))
                    onWonSet(wonPointPlayer, otherPlayer);
            }
        }
    }

    @Override
    public Optional<PlayerScore> getWinner(PlayerScore wonPlayer, PlayerScore otherPlayer) {
        if (wonPlayer.getSets() == SETS_WIN_COUNT_POINTS && otherPlayer.getSets() < SETS_WIN_COUNT_POINTS)
            return Optional.of(wonPlayer);

        if (otherPlayer.getSets() == SETS_WIN_COUNT_POINTS && wonPlayer.getSets() < SETS_WIN_COUNT_POINTS)
            return Optional.of(otherPlayer);

        return Optional.empty();
    }

    private void onWonTieBreak(PlayerScore wonPointPlayer, PlayerScore otherPlayer) {
        wonPointPlayer.resetPoints();
        otherPlayer.resetPoints();

        onWonSet(wonPointPlayer, otherPlayer);
    }

    private static void onWonGame(PlayerScore wonPlayer, PlayerScore otherPlayer) {
        wonPlayer.resetPoints();
        otherPlayer.resetPoints();
        wonPlayer.addGame();
    }

    private static void onWonSet(PlayerScore wonPlayer, PlayerScore otherPlayer) {
        wonPlayer.resetGames();
        otherPlayer.resetGames();
        wonPlayer.addSet();
    }
    private static boolean isSetWinner(PlayerScore wonPlayer, PlayerScore otherPlayer) {
        if(wonPlayer.getGames() > WON_SET_COUNT_GAMES && otherPlayer.getGames() == (WON_SET_COUNT_GAMES - 1))
            return true;

        return wonPlayer.getGames() == WON_SET_COUNT_GAMES && otherPlayer.getGames() < (WON_SET_COUNT_GAMES - 1);
    }
    private static void onWonPointInTieBreak(PlayerScore wonPlayer) {
        wonPlayer.addPoints(TIE_BREAK_COUNT_INCREASE_POINTS);
    }
    private static void onWonPoint(PlayerScore wonPlayer) {
        int increasePoints = hasMoreThanTwoPoints(wonPlayer) ? EQUALS_COUNT_INCREASE_POINTS : DEFAULT_COUNT_INCREASE_POINTS;
        wonPlayer.addPoints(increasePoints);
    }
    private static boolean hasMoreThanTwoPoints(PlayerScore player) {
        return player.getPoints() >= SECOND_POINT_COUNT_POINTS;
    }
    private static boolean isTieBreakWinner(PlayerScore tieBreakWinner, PlayerScore other) {
        if(!isTieBreak(tieBreakWinner, other))
            throw new RuntimeException();

        return tieBreakWinner.getPoints() == WIN_TIE_BREAK_COUNT_POINTS;
    }
    private static boolean isGameWinner(PlayerScore gameWinner, PlayerScore other) {
        if (gameWinner.getPoints() == GAME_WIN_COUNT_POINTS && other.getPoints() <= FIRST_POINT_COUNT_POINTS)
            return true;

        if (gameWinner.getPoints() > GAME_WIN_COUNT_POINTS && other.getPoints() == SECOND_POINT_COUNT_POINTS)
            return true;

        if(isPointsEquals(gameWinner, other)){
            //Если разрыв по очкам между игроком 1 и 2 идет в 2 очка в пользу gameWinner, то выйграл gameWinner
            return (gameWinner.getPoints() - other.getPoints()) == EQUALS_DOUBLE_COUNT_INCREASE_POINTS;
        }

        return false;
    }
    private static boolean isPointsEquals(PlayerScore firstPlayer, PlayerScore secondPlayer) {
        return firstPlayer.getPoints() >= GAME_WIN_COUNT_POINTS && secondPlayer.getPoints() >= GAME_WIN_COUNT_POINTS;
    }
    private static boolean isTieBreak(PlayerScore firstPlayer, PlayerScore secondPlayer) {
        return firstPlayer.getGames() == TIE_BREAK_COUNT_GAMES && secondPlayer.getGames() == TIE_BREAK_COUNT_GAMES;
    }
}