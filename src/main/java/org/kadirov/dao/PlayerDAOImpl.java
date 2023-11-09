package org.kadirov.dao;

import jakarta.persistence.NoResultException;
import org.kadirov.entity.PlayerEntity;

import java.util.List;
import java.util.Optional;

public class PlayerDAOImpl  implements PlayerDAO {

    private final EntityCRUDRepository<PlayerEntity, Integer> entityCRUDRepository;
    private final HibernateRepository hibernateRepository;

    public PlayerDAOImpl(HibernateRepository hibernateRepository) {
        this.hibernateRepository = hibernateRepository;
        this.entityCRUDRepository = new EntityCRUDRepository<>(this.hibernateRepository, PlayerEntity.class);
    }

    @Override
    public PlayerEntity findOrCreateByName(String name) {
        return hibernateRepository.execute(session -> {
            try {
                return session.createQuery("from "+PlayerEntity.class.getSimpleName()+" where name= :name", PlayerEntity.class)
                        .setParameter("name", name)
                        .getSingleResult();
            } catch (NoResultException nre){
                PlayerEntity newPlayer = new PlayerEntity(name);
                session.persist(newPlayer);
                return newPlayer;
            }
        });
    }
    @Override
    public Optional<PlayerEntity> findByName(String name) {
        return hibernateRepository.execute(session -> {
            try {
                PlayerEntity target = session.createQuery("from "+PlayerEntity.class.getSimpleName()+" where name= :name", PlayerEntity.class)
                        .setParameter("name", name)
                        .getSingleResult();

                return Optional.of(target);
            }catch (NoResultException nre){
                return Optional.empty();
            }
        });
    }
    @Override
    public List<PlayerEntity> findAll() {
        return entityCRUDRepository.findAll();
    }
    @Override
    public Optional<PlayerEntity> findById(Integer id) {
        return entityCRUDRepository.findById(id);
    }
    @Override
    public PlayerEntity save(PlayerEntity entity) {
        return entityCRUDRepository.save(entity);
    }
    @Override
    public PlayerEntity update(PlayerEntity entity) {
        return entityCRUDRepository.update(entity);
    }
    @Override
    public void deleteById(Integer id) {
        entityCRUDRepository.deleteById(id);
    }
}
