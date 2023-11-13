<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/error.css">
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <title>Tennis scoreboard</title>
</head>
<body>
    <div class="kw-container" id="main-menu-container">
        <p>Status code: ${error.statusCode()}</p>
        <p>Message: ${error.message()}</p>
    </div>
</body>
</html>