package org.kadirov.dao;

import org.kadirov.entity.PlayerEntity;

import java.util.Optional;

public interface PlayerDAO extends CRUDRepository<PlayerEntity, Integer> {
    PlayerEntity findOrCreateByName(String name);
    Optional<PlayerEntity> findByName(String name);
}
