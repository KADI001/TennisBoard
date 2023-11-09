package org.kadirov.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.dao.MatchDAO;
import org.kadirov.entity.MatchEntity;

import java.io.IOException;
import java.util.List;

@WebServlet("/match-history")
public class MatchHistoryServlet extends HttpServlet {

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

        if(appliedFilter){
            matches = matchDAO.findByNameOfFirstPlayerOrSecondPlayer(playerName);
        } else {
            matches = matchDAO.findAll();
        }

        req.setAttribute("applied_filter", appliedFilter);
        req.setAttribute("filter_by_player_name", playerName);
        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", pageStr == null ? DEFAULT_PAGE : Integer.parseInt(pageStr));
        req.setAttribute("matchAmountPerPage", MATCH_AMOUNT_PER_PAGE);
        req.setAttribute("pagePerSection", PAGE_PER_SECTION);
        req.setAttribute("maxPageAmount", (int) Math.ceil(matches.size() / (MATCH_AMOUNT_PER_PAGE + 0f)));

        req.getRequestDispatcher("pages/match-history.jsp").forward(req, resp);
    }
}
