package org.kadirov.service;

import org.kadirov.dao.match.MatchDAO;
import org.kadirov.dto.Pagination;
import org.kadirov.entity.MatchEntity;
import org.kadirov.util.PageUtil;

import java.util.List;

public class MatchServiceImpl implements MatchService {

    private final MatchDAO matchDAO;

    public MatchServiceImpl(MatchDAO matchDAO) {
        this.matchDAO = matchDAO;
    }

    @Override
    public Pagination<MatchEntity> getByNameWithPagination(String name, int page, int pageSize, int pageCount) {
        int count = Math.toIntExact(matchDAO.countWithFirstPlayerNameOrSecondPlayerName(name));

        int maxPageCount = (int) Math.ceil(count / (pageSize + 0f));
        int clampedPage = PageUtil.clamp(page, 1, count);
        int clampedPageCount = PageUtil.clamp(pageCount, 1, maxPageCount);

        int temp = (int) Math.ceil(clampedPage / (clampedPageCount + 0f));
        int from = (temp * clampedPageCount - clampedPageCount) + 1;
        int to = temp * clampedPageCount;

        int offset = (from - 1) * pageSize;
        int limit = (to - from + 1) * pageSize;

        List<MatchEntity> data = matchDAO.findByNameWithOffsetAndLimit(name, offset, limit);
        return new Pagination<>(from, to, pageSize, clampedPageCount, maxPageCount, data);
    }

    @Override
    public Pagination<MatchEntity> getWithPagination(int page, int pageSize, int pageCount) {
        int count = Math.toIntExact(matchDAO.count());

        int maxPageCount = (int) Math.ceil(count / (pageSize + 0f));
        int clampedPage = PageUtil.clamp(page, 1, count);
        int clampedPageCount = PageUtil.clamp(pageCount, 1, maxPageCount);

        int temp = (int) Math.ceil(clampedPage / (clampedPageCount + 0f));
        int from = (temp * clampedPageCount - clampedPageCount) + 1;
        int to = temp * clampedPageCount;

        int offset = (from - 1) * pageSize;
        int limit = (to - from + 1) * pageSize;

        List<MatchEntity> data = matchDAO.findAllWithOffsetAndLimit(offset, limit - 1);
        return new Pagination<>(from, to, pageSize, clampedPageCount, maxPageCount, data);
    }
}
