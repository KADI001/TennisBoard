<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/match-board.css">
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <title>Tennis scoreboard</title>
</head>
<body>
    <%@ page import = "org.kadirov.dao.*, org.kadirov.entity.*"%>

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
                    <td>${firstPlayerName}</td>
                    <td>${firstPlayerScore.getSets()}</td>
                    <td>${firstPlayerScore.getGames()}</td>
                    <td>${firstPlayerScore.getPoints()}</td>
                </tr>
                <tr>
                    <td>${secondPlayerName}</td>
                    <td>${secondPlayerScore.getSets()}</td>
                    <td>${secondPlayerScore.getGames()}</td>
                    <td>${secondPlayerScore.getPoints()}</td>
                </tr>
            </tbody>
        </table>
        
        <div id="actions-container">
            <form id="first-player-form" method="POST" action="/match-score">
                <input type="hidden" value="${firstPlayerScore.getId()}" name="winnerPlayerScoreId">
                <input type="hidden" value="${matchId}" name="matchId">
                <input class="kw-button" type="submit" value="Win point 1">
            </form>

            <form id="second-player-form" method="POST" action="/match-score">
                <input type="hidden" value="${secondPlayerScore.getId()}" name="winnerPlayerScoreId">
                <input type="hidden" value="${matchId}" name="matchId">
                <input class="kw-button" type="submit" value="Win point 2">
            </form>
        </div>        
    </div>

</body>
</html>