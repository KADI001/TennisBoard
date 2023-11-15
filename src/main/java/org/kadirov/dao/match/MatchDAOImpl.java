package org.kadirov.dao.match;

import jakarta.persistence.NoResultException;
import org.kadirov.dao.EntityCRUDRepository;
import org.kadirov.dao.HibernateFunc;
import org.kadirov.dao.HibernateRepository;
import org.kadirov.dto.Pagination;
import org.kadirov.entity.MatchEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchDAOImpl implements MatchDAO {

    private final EntityCRUDRepository<MatchEntity, Integer> entityCRUDRepository;
    private final HibernateRepository hibernateRepository;

    public MatchDAOImpl(HibernateRepository hibernateRepository) {
        this.hibernateRepository = hibernateRepository;
        this.entityCRUDRepository = new EntityCRUDRepository<>(hibernateRepository, MatchEntity.class);
    }

    @Override
    public List<MatchEntity> findAll() {
        return entityCRUDRepository.findAll();
    }

    @Override
    public Optional<MatchEntity> findById(Integer id) {
        return entityCRUDRepository.findById(id);
    }

    @Override
    public MatchEntity save(MatchEntity entity) {
        return entityCRUDRepository.save(entity);
    }

    @Override
    public MatchEntity update(MatchEntity entity) {
        return entityCRUDRepository.update(entity);
    }

    @Override
    public void deleteById(Integer id) {
        entityCRUDRepository.deleteById(id);
    }

    @Override
    public List<MatchEntity> findByNameOfFirstPlayerOrSecondPlayer(String name) {
        return hibernateRepository.execute(session -> {
            try {
                return session
                        .createQuery("from MatchEntity where firstPlayer.name = :name or secondPlayer.name = :name", MatchEntity.class)
                        .setParameter("name", name)
                        .getResultList();
            } catch (NoResultException nre) {
                return new ArrayList<>();
            }
        });
    }

    @Override
    public Long count() {
        return hibernateRepository.execute((HibernateFunc<Long>) session -> session
                .createQuery("select count(m) from MatchEntity m", Long.class)
                .getSingleResult());
    }

    @Override
    public Long countWithFirstPlayerNameOrSecondPlayerName(String name) {
        return hibernateRepository.execute((HibernateFunc<Long>) session -> session
                .createQuery("select count(m) from MatchEntity m where m.firstPlayer.name = :name or m.secondPlayer.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult());
    }

    @Override
    public List<MatchEntity> findAllWithOffsetAndLimit(int offset, int limit) {
        return hibernateRepository.execute((HibernateFunc<List<MatchEntity>>) session -> session
                .createQuery("from " + MatchEntity.class.getSimpleName(), MatchEntity.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList());
    }

    @Override
    public List<MatchEntity> findByNameWithOffsetAndLimit(String name, int offset, int limit) {
        return hibernateRepository.execute((HibernateFunc<List<MatchEntity>>) session -> session
                .createQuery("from MatchEntity where firstPlayer.name = :name or secondPlayer.name = :name", MatchEntity.class)
                .setParameter("name", name)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList());
    }
}
