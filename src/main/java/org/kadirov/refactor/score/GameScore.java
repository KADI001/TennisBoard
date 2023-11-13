package org.kadirov.refactor.score;

public class GameScore extends Score<Integer> {

    private Score<?> currentPointScore;

    public GameScore() {
        currentPointScore = new RegularPointScore();
    }

    @Override
    public Integer getZeroScore() {
        return 0;
    }

    @Override
    public void winPoint(Player player) {
        currentPointScore.winPoint(player);

        if(currentPointScore.state != ScoreState.ONGOING){
            onPointWon(player);
        }
    }

    public void onPointWon(Player player){
        setPlayerScore(player, getPlayerScore(player) + 1);

        this.currentPointScore = new RegularPointScore();

        if(firstPlayerScore == 6 && secondPlayerScore == 6)
            this.currentPointScore = new TieBrakePointScore();

        if(getPlayerScore(player) == 6 && getOppositePlayerScore(player) <= 4){
            setWinState(player);
            return;
        }

        if(getPlayerScore(player) > 6 && getOppositePlayerScore(player) == 5){
            setWinState(player);
            return;
        }

        state =  ScoreState.ONGOING;
    }
}
