package org.kadirov.service;

import org.kadirov.dto.Pagination;
import org.kadirov.entity.MatchEntity;

public interface MatchService {
    Pagination<MatchEntity> getByNameWithPagination(String name, int page, int pageSize, int pageCount);
    Pagination<MatchEntity> getWithPagination(int page, int pageSize, int pageCount);
}
