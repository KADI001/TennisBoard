package org.kadirov.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.dao.match.MatchDAO;
import org.kadirov.dto.MatchesHistoryView;
import org.kadirov.dto.Pagination;
import org.kadirov.entity.MatchEntity;
import org.kadirov.service.MatchService;
import org.kadirov.servlet.exception.BadRequestException;
import org.kadirov.servlet.exception.ExceptionMessages;
import org.kadirov.servlet.exception.InternalServerException;
import org.kadirov.util.PageUtil;

import java.io.IOException;

@WebServlet("/match-history")
public class MatchHistoryServlet extends BaseServlet {

    public static final int PAGES_PER_SECTION = 3;
    public static final int MATCH_AMOUNT_PER_PAGE = 5;
    public static final int DEFAULT_PAGE = 1;
    private MatchDAO matchDAO;
    private MatchService matchService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        matchDAO = (MatchDAO) servletContext.getAttribute("matchDAO");
        matchService = (MatchService) servletContext.getAttribute("matchService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");

        boolean appliedFilter = playerName != null && !playerName.isBlank();

        int currentPage;

        try {
            currentPage = pageStr == null ? DEFAULT_PAGE : Integer.parseInt(pageStr);
        } catch (Exception e){
            throw new BadRequestException(ExceptionMessages.NOT_VALID_PARAMETER("page"), e);
        }

        Pagination<MatchEntity> pagination;

        try {
            if(appliedFilter)
                pagination = matchService.getByNameWithPagination(playerName, currentPage, 5, 3);
            else
                pagination = matchService.getWithPagination(currentPage, 5, 3);
        } catch (Exception e){
            throw new InternalServerException(ExceptionMessages.DATA_BASE_ERROR(), e);
        }

        StringBuilder paginationHref = buildHref(appliedFilter, playerName);
        int previousPage = PageUtil.clamp(currentPage - 1, 1, pagination.maxAmount());
        int nextPage = PageUtil.clamp(currentPage + 1, 1, pagination.maxAmount());

        MatchesHistoryView matchesHistoryView = new MatchesHistoryView(
                appliedFilter, pagination, paginationHref.toString(),
                playerName, previousPage, currentPage, nextPage);

        req.setAttribute("matchesHistoryView", matchesHistoryView);

        req.getRequestDispatcher("pages/match-history.jsp").forward(req, resp);
    }

    private static StringBuilder buildHref(boolean appliedFilter, String playerName) {
        StringBuilder paginationHref = new StringBuilder("/match-history?");

        if(appliedFilter){
            paginationHref
                    .append("filter_by_player_name=")
                    .append(playerName)
                    .append("&");
        }

        paginationHref.append("page=");
        return paginationHref;
    }
}
