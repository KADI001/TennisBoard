package org.kadirov.dto;

import org.kadirov.entity.MatchEntity;

import java.util.List;

public record MatchesHistoryView(
        boolean appliedFilter,
        Pagination<MatchEntity> pagination,
        String paginationHref,
        String playerName,
        int previousPage,
        int currentPage,
        int nextPage
) {
}
