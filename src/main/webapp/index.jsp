<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/index.css">
    <title>Tennis scoreboard</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
    <div class="kw-container" id="main-menu-container">
        <h1 class="kw-title" id="title">Tennis Board</h1>

        <span id="actions-container">
            <a href="/startMatch">
                <input class="kw-button" id="start" type="submit" value="Начать новый">
            </a>

            <br>

            <a href="/match-history">
                <input class="kw-button" id="watch" type="submit" value="Посмотреть прошлые">
            </a>
        </span>
    </div>
</body>
</html>