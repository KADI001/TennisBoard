package org.kadirov.refactor.score;

public abstract class Score<T> {

    protected T firstPlayerScore;
    protected T secondPlayerScore;

    protected ScoreState state;

    public Score() {
        firstPlayerScore = getZeroScore();
        secondPlayerScore = getZeroScore();
        state = ScoreState.ONGOING;
    }

    public T getPlayerScore(Player player){
        return player == Player.ONE ? firstPlayerScore : secondPlayerScore;
    }

    public T getOppositePlayerScore(Player player){
        return player == Player.ONE ? secondPlayerScore : firstPlayerScore;
    }

    public void setPlayerScore(Player player, T value){
        if(player == Player.ONE)
            firstPlayerScore = value;
        else
            secondPlayerScore = value;
    }

    public void setOppositePlayerScore(Player player, T value){
        if(player == Player.ONE)
            secondPlayerScore = value;
        else
            firstPlayerScore = value;
    }

    public void setWinState(Player player){
        state = player == Player.ONE ?  ScoreState.FIRST_PLAYER_WON :  ScoreState.SECOND_PLAYER_WON;
    }

    public abstract T getZeroScore();
    public abstract void winPoint(Player player);
}
