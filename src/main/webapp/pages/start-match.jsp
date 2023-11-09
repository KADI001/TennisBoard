<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/start-match.css">
    <title>Tennis scoreboard</title>
</head>
<body>

    <div class="kw-container" id="start-match-container">
        <form method="POST" action="new-match">

            <div id="first-player-container">
                <label id="firts-player-name-label" for="fpn">First player name: </label>
                <input id="firts-player-name-textfield" type="text" name="firstPlayer">
            </div>

            <div id="second-player-container">
                <label id="second-player-name-label" for="spn">Second player name: </label>
                <input id="second-player-name-textfield" type="text" name="secondPlayer">
            </div>

            <div id="actions-container">
                <input id="back-button" type="submit" name="back" value="Back" formaction="/">
                <input id="start-button" type="submit" name="start" value="Start">
            </div>
        </form>
    </div>
</body>
</html>