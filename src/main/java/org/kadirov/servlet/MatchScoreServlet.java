package org.kadirov.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.dao.ActiveMatchesDAO;
import org.kadirov.dao.MatchDAO;
import org.kadirov.dao.PlayerScoreDAO;
import org.kadirov.entity.ActiveMatchEntity;
import org.kadirov.entity.MatchEntity;
import org.kadirov.entity.PlayerScoreEntity;
import org.kadirov.model.PlayerScore;
import org.kadirov.service.MatchService;
import org.kadirov.service.ScoreService;

import javax.management.RuntimeMBeanException;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    private MatchDAO matchDAO;
    private ActiveMatchesDAO activeMatchesDAO;
    private PlayerScoreDAO playerScoreDAO;
    private ScoreService scoreService;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();

        activeMatchesDAO = ((ActiveMatchesDAO) servletContext.getAttribute("activeMatchesDAO"));
        playerScoreDAO = ((PlayerScoreDAO) servletContext.getAttribute("playerScoreDAO"));
        scoreService = ((ScoreService) servletContext.getAttribute("scoreService"));
        matchDAO = (MatchDAO) servletContext.getAttribute("matchDAO");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchId = req.getParameter("uuid");

        if (matchId == null) {
            //todo: handle...
            return;
        }

        Optional<ActiveMatchEntity> optionalMatch = activeMatchesDAO.findById(matchId);

        if (optionalMatch.isPresent()) {
            ActiveMatchEntity activeMatch = optionalMatch.get();
            String url = "pages/match-board.jsp?firstPlayerId=" + activeMatch.getFirstPlayerScore().getPlayerId() +
                    "&secondPlayerId=" + activeMatch.getSecondPlayerScore().getPlayerId();
            req.getRequestDispatcher(url).forward(req, resp);
        } else {
            //todo: handle...
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int playerScoreId = Integer.parseInt(req.getParameter("playerId"));
        String matchUUID = req.getParameter("matchUUID");

        Optional<ActiveMatchEntity> optionalActiveMatch = activeMatchesDAO.findById(matchUUID);

        if (optionalActiveMatch.isPresent()) {
            ActiveMatchEntity activeMatch = optionalActiveMatch.get();
            PlayerScoreEntity firstPlayerScore = activeMatch.getFirstPlayerScore();
            PlayerScoreEntity secondPlayerScore = activeMatch.getSecondPlayerScore();

            PlayerScore wonPlayer;
            PlayerScore otherPlayer;

            if (firstPlayerScore.getId() == playerScoreId) {
                wonPlayer = firstPlayerScore;
                otherPlayer = secondPlayerScore;
            } else if (secondPlayerScore.getId() == playerScoreId) {
                wonPlayer = secondPlayerScore;
                otherPlayer = firstPlayerScore;
            } else{
                throw new RuntimeException();
            }

            scoreService.updateScore(wonPlayer, otherPlayer);

            Optional<PlayerScore> optionalWinner = scoreService.getWinner(wonPlayer, otherPlayer);

            optionalWinner.ifPresentOrElse(winner -> {
                try {
                    matchDAO.save(firstPlayerScore.getPlayerId(), secondPlayerScore.getPlayerId(), wonPlayer.getPlayerId());
                    activeMatchesDAO.deleteById(activeMatch.getId());
                    resp.sendRedirect("/match-winner?winnerId="+winner.getPlayerId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                try {
                    playerScoreDAO.update(activeMatch.getFirstPlayerScore());
                    playerScoreDAO.update(activeMatch.getSecondPlayerScore());
                    resp.sendRedirect("/match-score?uuid=" + matchUUID);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            //todo: handle ...
        }
    }
}
