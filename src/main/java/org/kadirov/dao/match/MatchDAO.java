package org.kadirov.dao.match;

import org.kadirov.dao.CRUDRepository;
import org.kadirov.dto.Pagination;
import org.kadirov.entity.MatchEntity;

import java.util.List;

public interface MatchDAO extends CRUDRepository<MatchEntity, Integer> {
    List<MatchEntity> findByNameOfFirstPlayerOrSecondPlayer(String name);
    Long count();
    Long countWithFirstPlayerNameOrSecondPlayerName(String name);
    List<MatchEntity> findAllWithOffsetAndLimit(int offset, int limit);
    List<MatchEntity> findByNameWithOffsetAndLimit(String name, int offset, int limit);
}
