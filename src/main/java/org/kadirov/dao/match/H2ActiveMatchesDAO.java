package org.kadirov.dao.match;

import org.kadirov.dao.EntityCRUDRepository;
import org.kadirov.dao.HibernateRepository;
import org.kadirov.entity.ActiveMatchEntity;

import java.util.List;
import java.util.Optional;

public class H2ActiveMatchesDAO implements ActiveMatchesDAO {

    private final EntityCRUDRepository<ActiveMatchEntity, String> entityCRUDRepository;

    public H2ActiveMatchesDAO(HibernateRepository hibernateRepository) {
        entityCRUDRepository = new EntityCRUDRepository<>(hibernateRepository, ActiveMatchEntity.class);
    }

    @Override
    public synchronized List<ActiveMatchEntity> findAll() {
        return entityCRUDRepository.findAll();
    }

    @Override
    public synchronized Optional<ActiveMatchEntity> findById(String id) {
        return entityCRUDRepository.findById(id);
    }

    @Override
    public synchronized ActiveMatchEntity save(ActiveMatchEntity entity) {
        return entityCRUDRepository.save(entity);
    }

    @Override
    public synchronized ActiveMatchEntity update(ActiveMatchEntity entity) {
        return entityCRUDRepository.update(entity);
    }

    @Override
    public synchronized void deleteById(String id) {
        entityCRUDRepository.deleteById(id);
    }
}