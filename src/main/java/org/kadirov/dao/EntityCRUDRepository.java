package org.kadirov.dao;

import java.util.List;
import java.util.Optional;

public class EntityCRUDRepository<TEntity, TIdentity> implements CRUDRepository<TEntity, TIdentity> {

    protected final HibernateRepository hibernateRepository;
    protected final Class<TEntity> entityType;

    public EntityCRUDRepository(HibernateRepository hibernateRepository, Class<TEntity> entityType) {
        this.hibernateRepository = hibernateRepository;
        this.entityType = entityType;
    }

    @Override
    public List<TEntity> findAll() {
        return hibernateRepository.execute((HibernateFunc<List<TEntity>>)
                session -> session.createQuery("from " + entityType.getSimpleName(), entityType).getResultList()
        );
    }

    @Override
    public Optional<TEntity> findById(TIdentity id) {
        TEntity entity = hibernateRepository.execute((HibernateFunc<TEntity>)
                session -> session.get(entityType, id)
        );
        return Optional.ofNullable(entity);
    }

    @Override
    public TEntity save(TEntity entity) {
        hibernateRepository.execute((HibernateAction)
                session -> session.persist(entity)
        );
        return entity;
    }

    @Override
    public TEntity update(TEntity entity) {
        return hibernateRepository.execute((HibernateFunc<TEntity>)
                session -> session.merge(entity)
        );
    }

    @Override
    public void deleteById(TIdentity id) {
        hibernateRepository.execute((HibernateAction)
                session -> session.remove(session.get(entityType, id))
        );
    }
}
