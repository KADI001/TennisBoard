package org.kadirov.service;

import org.kadirov.dao.ActiveMatchesDAO;
import org.kadirov.dao.PlayerScoreDAO;
import org.kadirov.dao.exception.DuplicateResourceException;
import org.kadirov.entity.ActiveMatchEntity;
import org.kadirov.entity.PlayerScoreEntity;
import org.kadirov.entity.PlayerEntity;
import org.kadirov.service.exception.MatchAlreadyActiveException;

public class MatchServiceImpl implements MatchService {

    public static final int WIN_AMOUNT_SETS = 2;
    private final ActiveMatchesDAO activeMatchesDAO;
    private final PlayerScoreDAO playerScoreDAO;

    public MatchServiceImpl(ActiveMatchesDAO activeMatchesDAO, PlayerScoreDAO playerScoreDAO) {
        this.activeMatchesDAO = activeMatchesDAO;
        this.playerScoreDAO = playerScoreDAO;
    }

    @Override
    public String start(PlayerEntity firstPlayer, PlayerEntity secondPlayer) throws MatchAlreadyActiveException {
        PlayerScoreEntity firstPlayerScore = new PlayerScoreEntity(firstPlayer.getId());
        PlayerScoreEntity secondPlayerScore = new PlayerScoreEntity(secondPlayer.getId());

        firstPlayerScore = playerScoreDAO.save(firstPlayerScore);
        secondPlayerScore = playerScoreDAO.save(secondPlayerScore);

        ActiveMatchEntity matchDTO = new ActiveMatchEntity(firstPlayerScore, secondPlayerScore);

        try {
            return activeMatchesDAO.save(matchDTO).getId();
        }catch (DuplicateResourceException dre){
            throw new MatchAlreadyActiveException("Failed to start match", dre);
        }
    }
}
