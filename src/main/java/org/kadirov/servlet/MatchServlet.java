package org.kadirov.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.dao.PlayerDAOImpl;
import org.kadirov.entity.PlayerEntity;
import org.kadirov.service.MatchService;
import org.kadirov.service.exception.MatchAlreadyActiveException;

import java.io.IOException;
import java.util.UUID;

@WebServlet(value = "/new-match")
public class MatchServlet extends HttpServlet {

    private PlayerDAOImpl playerDAOImpl;
    private MatchService matchService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        playerDAOImpl = ((PlayerDAOImpl) servletContext.getAttribute("playerDAO"));
        matchService = ((MatchService) servletContext.getAttribute("matchService"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstPlayerName = req.getParameter("firstPlayer");
        String secondPlayerName = req.getParameter("secondPlayer");

        if(firstPlayerName == null){
            //todo: handle...
            return;
        }

        if(secondPlayerName == null){
            //todo: handle...
            return;
        }

        PlayerEntity firstPlayerEntity = playerDAOImpl.findOrCreateByName(firstPlayerName);
        PlayerEntity secondPlayerEntity = playerDAOImpl.findOrCreateByName(secondPlayerName);
        String matchUUID;

        try {
            matchUUID = matchService.start(firstPlayerEntity, secondPlayerEntity);
        } catch (MatchAlreadyActiveException e) {
            //todo: handle...
            return;
        }

        resp.sendRedirect("/match-score?uuid=" + matchUUID);
    }
}
