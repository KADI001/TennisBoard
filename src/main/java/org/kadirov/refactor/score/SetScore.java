package org.kadirov.refactor.score;

public class SetScore extends Score<Integer> {

    private Score<?> gameScore;

    public SetScore() {
        gameScore = new GameScore();
    }

    @Override
    public Integer getZeroScore() {
        return 0;
    }

    @Override
    public void winPoint(Player player) {
        gameScore.winPoint(player);

        if(gameScore.state !=  ScoreState.ONGOING){
            onSetWon(player);
        }
    }

    private void onSetWon(Player player) {
        setPlayerScore(player, getPlayerScore(player) + 1);

        gameScore = new GameScore();

        if(getPlayerScore(player) == 2 && getOppositePlayerScore(player) < 2){
            setWinState(player);
        }
    }
}
