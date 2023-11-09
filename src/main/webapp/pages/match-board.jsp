<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/match-board.css">
    <title>Tennis scoreboard</title>
</head>
<body>
    <%@ page import = "org.kadirov.dao.*, org.kadirov.entity.*"%>

    <%  
        String matchId = request.getParameter("uuid");
        Integer firstPlayerId = Integer.parseInt(request.getParameter("firstPlayerId"));
        Integer secondPlayerId = Integer.parseInt(request.getParameter("secondPlayerId"));
        ServletContext servletContext = request.getServletContext();
        ActiveMatchesDAO activeMatchesDAO = (ActiveMatchesDAO) servletContext.getAttribute("activeMatchesDAO");
        PlayerDAO playerDAO = (PlayerDAO) servletContext.getAttribute("playerDAO");

        ActiveMatchEntity activeMatch = activeMatchesDAO.findById(matchId).get();

        PlayerScoreEntity firstPlayer = activeMatch.getFirstPlayerScore();
        PlayerScoreEntity secondPlayer = activeMatch.getSecondPlayerScore();
        String firstPlayerName = playerDAO.findById(firstPlayer.getPlayerId()).get().getName();
        String secondPlayerName = playerDAO.findById(secondPlayer.getPlayerId()).get().getName();
    %>

    <div class="kw-container" id="container">
        <table class="kw-table-header kw-table-rows" id="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Sets</th>
                    <th>Games</th>
                    <th>Points</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%=firstPlayerName%></td>
                    <td><%=firstPlayer.getSets()%></td>
                    <td><%=firstPlayer.getGames()%></td>
                    <td><%=firstPlayer.getPoints()%></td>
                </tr>
                <tr>
                    <td><%=secondPlayerName%></td>
                    <td><%=secondPlayer.getSets()%></td>
                    <td><%=secondPlayer.getGames()%></td>
                    <td><%=secondPlayer.getPoints()%></td>
                </tr>
            </tbody>
        </table>
        
        <div id="actions-container">
            <form id="first-player-form" method="POST" action="/match-score">
                <input type="hidden" value="<%=firstPlayer.getId()%>" name="playerId">
                <input type="hidden" value="<%=matchId%>" name="matchUUID">
                <input class="kw-button" type="submit" value="Win point 1">
            </form>

            <form id="second-player-form" method="POST" action="/match-score">
                <input type="hidden" value="<%=secondPlayer.getId()%>" name="playerId">
                <input type="hidden" value="<%=matchId%>" name="matchUUID">
                <input class="kw-button" type="submit" value="Win point 2">
            </form>
        </div>        
    </div>

</body>
</html>