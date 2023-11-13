package org.kadirov.refactor.score;

public class RegularPointScore extends Score<Point> {

    @Override
    public Point getZeroScore() {
        return Point.ZERO;
    }

    @Override
    public void winPoint(Player player) {
        Point playerScore = getPlayerScore(player);

        if(playerScore.ordinal() <= Point.THIRTY.ordinal()){
            // 30:X, 15:X, 0:X
            setPlayerScore(player, getPlayerScore(player).next());
            state =  ScoreState.ONGOING;
        } else if (playerScore.ordinal() == Point.FORTY.ordinal()) {
            // 40:X
            if(getOppositePlayerScore(player) == Point.ADVANTAGE){
                // 40:AD
                setOppositePlayerScore(player, Point.FORTY);
                state =  ScoreState.ONGOING;
            } else if(getOppositePlayerScore(player) == Point.FORTY){
                // 40:40
                setPlayerScore(player, getPlayerScore(player).next());
                state =  ScoreState.ONGOING;
            } else {
                // 40:30, 40:15, 40:0
                setWinState(player);
            }
        } else if(playerScore == Point.ADVANTAGE) {
            // AD:40
            setWinState(player);
        } else {
            throw new RuntimeException();
        }
    }
}
