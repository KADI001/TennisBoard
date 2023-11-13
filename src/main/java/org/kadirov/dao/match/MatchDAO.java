package org.kadirov.dao.match;

import org.kadirov.dao.CRUDRepository;
import org.kadirov.entity.MatchEntity;

import java.util.List;

public interface MatchDAO extends CRUDRepository<MatchEntity, Integer> {
    List<MatchEntity> findByNameOfFirstPlayerOrSecondPlayer(String name);
    MatchEntity save(int firstPlayerId, int secondPlayerId, int winnerId);
}
