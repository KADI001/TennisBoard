package org.kadirov.service;

import org.kadirov.dao.match.ActiveMatchesDAO;
import org.kadirov.entity.ActiveMatchEntity;
import org.kadirov.entity.PlayerScoreEntity;
import org.kadirov.entity.PlayerEntity;

public class ActiveMatchServiceImpl implements ActiveMatchService {

    private final ActiveMatchesDAO activeMatchesDAO;

    public ActiveMatchServiceImpl(ActiveMatchesDAO activeMatchesDAO) {
        this.activeMatchesDAO = activeMatchesDAO;
    }

    @Override
    public String start(String firstPlayerName, String secondPlayerName) {
        PlayerEntity firstPlayer = new PlayerEntity(firstPlayerName);
        PlayerEntity secondPlayer = new PlayerEntity(secondPlayerName);

        PlayerScoreEntity firstPlayerScore = new PlayerScoreEntity(firstPlayer);
        PlayerScoreEntity secondPlayerScore = new PlayerScoreEntity(secondPlayer);

        ActiveMatchEntity matchDTO = new ActiveMatchEntity(firstPlayerScore, secondPlayerScore);

        return activeMatchesDAO.save(matchDTO).getId();
    }
}
