package org.kadirov.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.dao.match.MatchDAO;
import org.kadirov.dto.MatchesHistoryView;
import org.kadirov.entity.MatchEntity;
import org.kadirov.servlet.exception.BadRequestException;
import org.kadirov.servlet.exception.ExceptionMessages;
import org.kadirov.servlet.exception.InternalServerException;

import java.io.IOException;
import java.util.List;

@WebServlet("/match-history")
public class MatchHistoryServlet extends BaseServlet {

    public static final int PAGE_PER_SECTION = 3;
    public static final int MATCH_AMOUNT_PER_PAGE = 5;
    public static final int DEFAULT_PAGE = 1;
    private MatchDAO matchDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        matchDAO = (MatchDAO) servletContext.getAttribute("matchDAO");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");

        boolean appliedFilter = playerName != null && !playerName.isBlank();

        List<MatchEntity> matches;

        try {
            if(appliedFilter){
                matches = matchDAO.findByNameOfFirstPlayerOrSecondPlayer(playerName);
            } else {
                matches = matchDAO.findAll();
            }
        }catch (Exception e){
            throw new InternalServerException(ExceptionMessages.DATA_BASE_ERROR(), e);
        }

        int maxPageAmount = (int) Math.ceil(matches.size() / (MATCH_AMOUNT_PER_PAGE + 0f));
        int currentPage;

        try {
            currentPage = pageStr == null ? DEFAULT_PAGE : Integer.parseInt(pageStr);

            if(currentPage <= 0)
                currentPage = 1;
            else if(currentPage > maxPageAmount)
                currentPage = maxPageAmount;

        } catch (Exception e){
            throw new BadRequestException(ExceptionMessages.NOT_VALID_PARAMETER("page"), e);
        }

        int g = (int) Math.ceil(currentPage / (PAGE_PER_SECTION + 0f));
        int paginationFrom = (g * PAGE_PER_SECTION - PAGE_PER_SECTION) + 1;
        int paginationTo = g * PAGE_PER_SECTION;

        int listFrom = MATCH_AMOUNT_PER_PAGE * (currentPage - 1);
        int listTo = Math.min((listFrom + MATCH_AMOUNT_PER_PAGE) - 1,  matches.size() - 1);

        StringBuilder paginationHref = new StringBuilder("/match-history?");

        if(appliedFilter){
            paginationHref
                    .append("filter_by_player_name=")
                    .append(playerName)
                    .append("&");
        }

        paginationHref.append("page=");

        MatchesHistoryView matchesHistoryView = new MatchesHistoryView(
                appliedFilter, paginationFrom, paginationTo,
                listFrom, listTo, paginationHref.toString(),
                playerName, matches, currentPage, maxPageAmount);

        req.setAttribute("matchesHistoryView", matchesHistoryView);

        req.getRequestDispatcher("pages/match-history.jsp").forward(req, resp);
    }
}
