package org.kadirov.dao.player;

import org.kadirov.dao.EntityCRUDRepository;
import org.kadirov.dao.HibernateRepository;
import org.kadirov.entity.PlayerScoreEntity;

import java.util.List;
import java.util.Optional;

public class H2PlayerScoreDAO implements PlayerScoreDAO {

    private final EntityCRUDRepository<PlayerScoreEntity, Integer> entityCRUDRepository;

    public H2PlayerScoreDAO(HibernateRepository hibernateRepository) {
        entityCRUDRepository = new EntityCRUDRepository<>(hibernateRepository, PlayerScoreEntity.class);
    }

    @Override
    public synchronized List<PlayerScoreEntity> findAll() {
        return entityCRUDRepository.findAll();
    }

    @Override
    public synchronized Optional<PlayerScoreEntity> findById(Integer id) {
        return entityCRUDRepository.findById(id);
    }

    @Override
    public synchronized PlayerScoreEntity save(PlayerScoreEntity entity) {
        return entityCRUDRepository.save(entity);
    }

    @Override
    public synchronized PlayerScoreEntity update(PlayerScoreEntity entity) {
        return entityCRUDRepository.update(entity);
    }

    @Override
    public synchronized void deleteById(Integer id) {
        entityCRUDRepository.deleteById(id);
    }
}
