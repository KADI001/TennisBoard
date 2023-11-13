<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/match-history.css">
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <title>Tennis scoreboard</title>
</head>
<body>
    <%@ page import = "org.kadirov.entity.*, org.kadirov.dao.MatchDAO, java.util.List, org.kadirov.util.PageUtil"%>

    <div id="matches-container" class="kw-container">

        <div id="filter-container">
            <form id="filter-form" method="GET" action="/match-history">
                <input class="kw-button" type="textfield" name="filter_by_player_name" placeholder="Player Name">
                <input class="kw-button" id="filter-search-button" type="submit" value="Search">
            </form>
        </div>

        <table class="kw-table-header kw-table-rows" id="match-table">
            <thead>
                <tr>
                    <th>First</th>
                    <th>Second</th>
                    <th>Winner</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="i" begin="${matchesHistoryView.listFrom()}" end="${matchesHistoryView.listTo()}" >
                    <tbody>
                        <td>${matchesHistoryView.matches().get(i).getFirstPlayer().getName()}</td>
                        <td>${matchesHistoryView.matches().get(i).getSecondPlayer().getName()}</td>
                        <td>${matchesHistoryView.matches().get(i).getWinner().getName()}</td>
                    </tbody>
                </c:forEach>
            </tbody>
        </table>

        <div id="page-navigation-container">
                <a id="previous-page" href="${matchesHistoryView.paginationHref()}${PageUtil.clamp(currentPage - 1, 1, maxPageAmount)}"> < </a>

                <c:forEach var="page" begin="${matchesHistoryView.paginationFrom()}" end="${matchesHistoryView.paginationTo()}">

                    <c:choose>

                        <c:when test="${page == matchesHistoryView.currentPage()}">
                            <a id="page-${page}" href="${matchesHistoryView.paginationHref()}${page}" data-selected>${page}</a>
                        </c:when>

                        <c:when test="${page <= maxPageAmount}">
                            <a id="page-${page}" href="${matchesHistoryView.paginationHref()}${page}">${page}</a>
                        </c:when>

                    </c:choose>

                </c:forEach>

                <a id="previous-page" href="${matchesHistoryView.paginationHref()}${PageUtil.clamp(currentPage + 1, 1, maxPageAmount)}"> > </a>
            </div>

        <div id="actions-container">
            <form method="GET" action="/">
                <input class="kw-button" id="go-back-button" type="submit" value="Back">
            </form>
        </div>
    </div>
</body>
</html>