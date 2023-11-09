package org.kadirov.dao;

import org.kadirov.entity.ActiveMatchEntity;
import org.kadirov.model.ActiveMatch;

public interface ActiveMatchesDAO extends CRUDRepository<ActiveMatchEntity, String> {
    void deleteByFirstPlayerNameAndSecondPlayerName(String firstPlayerName, String secondPlayerName);
}
