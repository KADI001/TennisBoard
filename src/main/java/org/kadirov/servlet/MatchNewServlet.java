package org.kadirov.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.service.ActiveMatchService;
import org.kadirov.servlet.exception.BadRequestException;
import org.kadirov.servlet.exception.ExceptionMessages;
import org.kadirov.servlet.exception.InternalServerException;

import java.io.IOException;

@WebServlet(value = "/match-new")
public class MatchNewServlet extends BaseServlet {

    private ActiveMatchService activeMatchService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        activeMatchService = ((ActiveMatchService) servletContext.getAttribute("activeMatchService"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/match-new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String firstPlayerName = req.getParameter("firstPlayer");
        String secondPlayerName = req.getParameter("secondPlayer");

        if (firstPlayerName == null)
            throw new BadRequestException(ExceptionMessages.MISSING_PARAMETER("firstPlayerName"));

        if (secondPlayerName == null)
            throw new BadRequestException(ExceptionMessages.MISSING_PARAMETER("secondPlayerName"));

        String matchUUID;

        try {
            matchUUID = activeMatchService.start(firstPlayerName, secondPlayerName);
        } catch (Exception e) {
            throw new InternalServerException(ExceptionMessages.DATA_BASE_ERROR(), e);
        }

        resp.sendRedirect("/match-score?uuid=" + matchUUID);
    }
}
