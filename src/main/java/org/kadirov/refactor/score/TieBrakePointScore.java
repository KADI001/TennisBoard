package org.kadirov.refactor.score;

public class TieBrakePointScore extends Score<Integer> {
    @Override
    public Integer getZeroScore() {
        return 0;
    }

    @Override
    public void winPoint(Player player) {
        int playerScore = getPlayerScore(player);

        if (playerScore <= 5) {
            // 1:X, 2:X, 3:X, 4:X, 5:X,
            setPlayerScore(player, getPlayerScore(player) + 1);
            state =  ScoreState.ONGOING;
        } else {
            Integer oppositePlayerScore = getOppositePlayerScore(player);

            if (playerScore == 6 && oppositePlayerScore <= 5) {
                // 6:5, 6:4, 6:3, 6:2, 6:1, 6:0,
                setPlayerScore(player, getPlayerScore(player) + 1);
                setWinState(player);
            } else if (playerScore == 6 && oppositePlayerScore == 6) {
                // 6:6
                setPlayerScore(player, getPlayerScore(player) + 1);
                state =  ScoreState.ONGOING;
            } else if((playerScore - oppositePlayerScore) == 1){
                // 7:6, 8:7, 9:7
                setPlayerScore(player, getPlayerScore(player) + 1);
                setWinState(player);
            }
        }
    }
}
