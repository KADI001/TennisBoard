package org.kadirov.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.dao.PlayerDAO;
import org.kadirov.entity.PlayerEntity;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/match-winner")
public class MatchWinnerServlet extends HttpServlet {

    private PlayerDAO playerDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        playerDAO = (PlayerDAO) servletContext.getAttribute("playerDAO");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int winnerId = Integer.parseInt(req.getParameter("winnerId"));

        Optional<PlayerEntity> optionalPlayer = playerDAO.findById(winnerId);

        if(optionalPlayer.isPresent()){
            req.getRequestDispatcher("pages/match-winner.jsp?name=" + optionalPlayer.get().getName()).forward(req, resp);
        }else {
            //todo: handle...
        }
    }
}
