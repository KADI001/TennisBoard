package org.kadirov.dao;

import org.kadirov.entity.ActiveMatchEntity;

import java.util.List;
import java.util.Optional;

public class H2ActiveMatchesDAO implements ActiveMatchesDAO {

    private final EntityCRUDRepository<ActiveMatchEntity, String> entityCRUDRepository;

    public H2ActiveMatchesDAO(HibernateRepository hibernateRepository) {
        entityCRUDRepository = new EntityCRUDRepository<>(hibernateRepository, ActiveMatchEntity.class);
    }

    @Override
    public void deleteByFirstPlayerNameAndSecondPlayerName(String firstPlayerName, String secondPlayerName) {

    }

    @Override
    public List<ActiveMatchEntity> findAll() {
        return entityCRUDRepository.findAll();
    }

    @Override
    public Optional<ActiveMatchEntity> findById(String id) {
        return entityCRUDRepository.findById(id);
    }

    @Override
    public ActiveMatchEntity save(ActiveMatchEntity entity) {
        return entityCRUDRepository.save(entity);
    }

    @Override
    public ActiveMatchEntity update(ActiveMatchEntity entity) {
        return entityCRUDRepository.update(entity);
    }

    @Override
    public void deleteById(String id) {
        entityCRUDRepository.deleteById(id);
    }
}
