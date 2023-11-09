package org.kadirov.service;

import org.kadirov.entity.ActiveMatchEntity;
import org.kadirov.entity.PlayerEntity;
import org.kadirov.service.exception.MatchAlreadyActiveException;

public interface MatchService {
        String start(PlayerEntity firstPlayer, PlayerEntity secondPlayer) throws MatchAlreadyActiveException;
}
