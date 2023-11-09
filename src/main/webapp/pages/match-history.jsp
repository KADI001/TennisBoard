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
    <%
        ServletContext sc = request.getServletContext();

        String pageStr = request.getParameter("page");
        String playerName = request.getParameter("filter_by_player_name");

        List<MatchEntity> matches = (List<MatchEntity>) request.getAttribute("matches");

        int matchAmountPerPage = (int) request.getAttribute("matchAmountPerPage");
        int currentPage = (int) request.getAttribute("matchAmountPerPage");
        int maxPageAmount = (int) request.getAttribute("maxPageAmount");
        int pagePerSection = (int) request.getAttribute("pagePerSection");
    %>

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
                <%
                    int from = matchAmountPerPage * (currentPage - 1);
                    int to = from + matchAmountPerPage;
                    to = to > matches.size() ? matches.size() : to;

                    for(int i = from; i < to; i++){
                        MatchEntity match = matches.get(i);
                        out.println("<tr>");
                        out.println(String.format("<td>%s</td>", match.getFirstPlayer().getName()));
                        out.println(String.format("<td>%s</td>", match.getSecondPlayer().getName()));
                        out.println(String.format("<td>%s</td>", match.getWinner().getName()));
                        out.println("</tr>");
                    }
                %>
                <c:forEach var="match" items
            </tbody>
        </table>
        <div id="page-navigation-container">
                <%
                    String prefixHref = "/match-history?";

                    if(playerName != null && !playerName.isBlank()){
                        prefixHref += "filter_by_player_name=" + playerName + "&page=";
                    }else{
                        prefixHref += "page=";
                    }
                %>
                <a id="previous-page" href="<%=prefixHref + PageUtil.clamp(currentPage - 1, 1, maxPageAmount)%>"> < </a>
                <%
                    int g = (int) Math.ceil(currentPage / (pagePerSection + 0f));
                    int f = (g * pagePerSection - pagePerSection) + 1;
                    int t = g * pagePerSection;

                    for(int i = f; i <= t; i++) {
                        String elemIdPostFix = String.valueOf(i);
                        String selected = "";
                        String placeholder = "";
                        String href = prefixHref + i;
                        String content = String.valueOf(i);

                        if(i == currentPage){
                            selected = "data-selected";
                        }

                        if(i > maxPageAmount){
                            href = "#";
                            placeholder = "disabled";
                            content = "#";
                        }

                        out.println(String.format("<a id=\"page-%s\" href=\"%s\" %s %s> %s </a>", elemIdPostFix, href, selected, placeholder, content));
                    }
                %>
                <a id="next-page" href="<%=prefixHref + PageUtil.clamp(currentPage + 1, 1, maxPageAmount)%>"> > </a>
            </div>
        <div id="actions-container">
            <form method="GET" action="/">
                <input class="kw-button" id="go-back-button" type="submit" value="Back">
            </form>
        </div>
    </div>
</body>
</html>