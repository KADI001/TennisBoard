package org.kadirov.dao;

import jakarta.persistence.NoResultException;
import org.kadirov.entity.MatchEntity;
import org.kadirov.entity.PlayerEntity;

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
                List<MatchEntity> resultList = session.createQuery("from MatchEntity where firstPlayer.name = :name or secondPlayer.name = :name", MatchEntity.class)
                        .setParameter("name", name)
                        .getResultList();
                return resultList;
            } catch (NoResultException nre) {
                return new ArrayList<>();
            }
        });
    }

    @Override
    public MatchEntity save(int firstPlayerId, int secondPlayerId, int winnerId) {
        return hibernateRepository.execute(session -> {
            MatchEntity matchEntity = new MatchEntity();
            matchEntity.setFirstPlayer(new PlayerEntity(firstPlayerId));
            matchEntity.setSecondPlayer(new PlayerEntity(secondPlayerId));
            matchEntity.setWinner(new PlayerEntity(winnerId));
            session.persist(matchEntity);
            return matchEntity;
        });
    }
}
