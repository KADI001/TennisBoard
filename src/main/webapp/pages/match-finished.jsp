<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/match-finished.css">
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <title>Tennis scoreboard</title>
</head>
<body>
    <div class="kw-container" id="container">
        <h1 class="kw-container-title">Match Finished!</h1>
        <table class="kw-table-header kw-table-rows" id="table">
            <thead class="table-header">
                <tr>
                    <th>Name</th>
                    <th>Sets</th>
                </tr>
            </thead>
            <tbody class="table-body">
                <tr>
                    <td>${matchFinishedView.firstPlayerName()}</td>
                    <td>${matchFinishedView.firstPlayerSets()}</td>
                </tr>
                <tr>
                    <td>${matchFinishedView.secondPlayerName()}</td>
                    <td>${matchFinishedView.secondPlayerSets()}</td>
                </tr>
            </tbody>
        </table>

        <div class="action-container">
            <form class="action-form" method="GET" action="/">
                <input class="action-main-menu-button kw-button" type="submit" value="Main menu">
            </form>
        </div>
    </div>

</body>
</html>