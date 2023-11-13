package org.kadirov.dto;

import org.kadirov.entity.MatchEntity;

import java.util.List;

public record MatchesHistoryView(
        boolean appliedFilter,
        int paginationFrom,
        int paginationTo,
        int listFrom,
        int listTo,
        String paginationHref,
        String playerName,
        List<MatchEntity> matches,
        int currentPage,
        int maxPageAmount
) {
}
