package org.kadirov.dao;

import org.kadirov.entity.ActiveMatchEntity;
import org.kadirov.entity.PlayerScoreEntity;

import java.util.List;
import java.util.Optional;

public class H2PlayerScoreDAO implements PlayerScoreDAO {

    private final EntityCRUDRepository<PlayerScoreEntity, Integer> entityCRUDRepository;

    public H2PlayerScoreDAO(HibernateRepository hibernateRepository) {
        entityCRUDRepository = new EntityCRUDRepository<>(hibernateRepository, PlayerScoreEntity.class);
    }

    @Override
    public List<PlayerScoreEntity> findAll() {
        return entityCRUDRepository.findAll();
    }

    @Override
    public Optional<PlayerScoreEntity> findById(Integer id) {
        return entityCRUDRepository.findById(id);
    }

    @Override
    public PlayerScoreEntity save(PlayerScoreEntity entity) {
        return entityCRUDRepository.save(entity);
    }

    @Override
    public PlayerScoreEntity update(PlayerScoreEntity entity) {
        return entityCRUDRepository.update(entity);
    }

    @Override
    public void deleteById(Integer id) {
        entityCRUDRepository.deleteById(id);
    }
}
