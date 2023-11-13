package org.kadirov;

import org.junit.jupiter.api.Test;
import org.kadirov.refactor.score.Player;
import org.kadirov.refactor.score.SetScore;

public class OtherTests {

    @Test
    public void test(){
        SetScore setScore = new SetScore();
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);

        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);

        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);

        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);

        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);

        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);

        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);
        setScore.winPoint(Player.ONE);

        System.out.println("Test!");
    }
}
