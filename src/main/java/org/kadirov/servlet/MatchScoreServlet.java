package org.kadirov.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kadirov.dao.match.ActiveMatchesDAO;
import org.kadirov.dao.match.MatchDAO;
import org.kadirov.dao.player.PlayerDAO;
import org.kadirov.dao.player.PlayerScoreDAO;
import org.kadirov.dto.MatchFinishedView;
import org.kadirov.entity.ActiveMatchEntity;
import org.kadirov.entity.MatchEntity;
import org.kadirov.entity.PlayerEntity;
import org.kadirov.entity.PlayerScoreEntity;
import org.kadirov.service.PlayerScore;
import org.kadirov.service.MatchScoreService;
import org.kadirov.servlet.exception.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/match-score")
public class MatchScoreServlet extends BaseServlet {

    private MatchDAO matchDAO;
    private PlayerDAO playerDAO;
    private ActiveMatchesDAO activeMatchesDAO;
    private PlayerScoreDAO playerScoreDAO;
    private MatchScoreService matchScoreService;

    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();

        activeMatchesDAO = (ActiveMatchesDAO) servletContext.getAttribute("activeMatchesDAO");
        playerScoreDAO = (PlayerScoreDAO) servletContext.getAttribute("playerScoreDAO");
        matchScoreService = (MatchScoreService) servletContext.getAttribute("scoreService");
        matchDAO = (MatchDAO) servletContext.getAttribute("matchDAO");
        playerDAO = (PlayerDAO) servletContext.getAttribute("playerDAO");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchId = req.getParameter("uuid");

        if (matchId == null)
            throw new BadRequestException(ExceptionMessages.MISSING_PARAMETER("uuid"));

        Optional<ActiveMatchEntity> optionalMatch;

        try {
            optionalMatch = activeMatchesDAO.findById(matchId);
        } catch (Exception e) {
            throw new InternalServerException(ExceptionMessages.DATA_BASE_ERROR(), e);
        }

        if (optionalMatch.isPresent()) {
            ActiveMatchEntity activeMatch = optionalMatch.get();

            PlayerScoreEntity firstPlayerScore = activeMatch.getFirstPlayerScore();
            PlayerScoreEntity secondPlayerScore = activeMatch.getSecondPlayerScore();

            req.setAttribute("matchId", matchId);
            req.setAttribute("firstPlayerName", firstPlayerScore.getPlayer().getName());
            req.setAttribute("secondPlayerName", secondPlayerScore.getPlayer().getName());
            req.setAttribute("firstPlayerScore", firstPlayerScore);
            req.setAttribute("secondPlayerScore", secondPlayerScore);

            req.getRequestDispatcher("pages/match-board.jsp").forward(req, resp);
        } else {
            throw new NotFoundException("There is no one match with that id");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String winnerPlayerScoreIdStr = req.getParameter("winnerPlayerScoreId");

        if (winnerPlayerScoreIdStr == null)
            throw new BadRequestException(ExceptionMessages.MISSING_PARAMETER("winnerPlayerScoreId"));

        int playerScoreId;

        try {
            playerScoreId = Integer.parseInt(winnerPlayerScoreIdStr);
        } catch (Exception e) {
            throw new BadRequestException(ExceptionMessages.NOT_VALID_PARAMETER("winnerPlayerScoreId"), e);
        }

        String matchId = req.getParameter("matchId");

        if (matchId == null)
            throw new BadRequestException(ExceptionMessages.MISSING_PARAMETER("matchId"));

        Optional<ActiveMatchEntity> optionalActiveMatch;

        try {
            optionalActiveMatch = activeMatchesDAO.findById(matchId);
        } catch (Exception e) {
            throw new InternalServerException(ExceptionMessages.DATA_BASE_ERROR(), e);
        }

        if (optionalActiveMatch.isPresent()) {
            ActiveMatchEntity activeMatch = optionalActiveMatch.get();
            PlayerScoreEntity firstPlayerScore = activeMatch.getFirstPlayerScore();
            PlayerScoreEntity secondPlayerScore = activeMatch.getSecondPlayerScore();

            PlayerScore wonPointPlayer;
            PlayerScore otherPlayer;

            if (firstPlayerScore.getId() == playerScoreId) {
                wonPointPlayer = firstPlayerScore;
                otherPlayer = secondPlayerScore;
            } else if (secondPlayerScore.getId() == playerScoreId) {
                wonPointPlayer = secondPlayerScore;
                otherPlayer = firstPlayerScore;
            } else {
                throw new ConflictException("The match with that id has no player with this id");
            }

            matchScoreService.updateScore(wonPointPlayer, otherPlayer);

            Optional<PlayerScore> optionalWinner = matchScoreService.getWinner(wonPointPlayer, otherPlayer);

            if (optionalWinner.isPresent()) {
                PlayerScore h2WinnerPlayer = optionalWinner.get();

                try {
                    PlayerEntity h2FirstPlayer = activeMatch.getFirstPlayerScore().getPlayer();
                    PlayerEntity h2SecondPlayer = activeMatch.getSecondPlayerScore().getPlayer();

                    PlayerEntity firstPlayer = playerDAO.findOrCreateByName(h2FirstPlayer.getName());
                    PlayerEntity secondPlayer = playerDAO.findOrCreateByName(h2SecondPlayer.getName());
                    PlayerEntity winnerPlayer = h2WinnerPlayer.getPlayer().getName().equals(firstPlayer.getName()) ? firstPlayer : secondPlayer;

                    MatchEntity matchEntity = new MatchEntity(firstPlayer, secondPlayer, winnerPlayer);

                    matchDAO.save(matchEntity);

                    activeMatchesDAO.deleteById(activeMatch.getId());
                } catch (Exception e) {
                    throw new InternalServerException(ExceptionMessages.DATA_BASE_ERROR(), e);
                }

                MatchFinishedView matchFinishedView = new MatchFinishedView(
                        firstPlayerScore.getPlayer().getName(),
                        secondPlayerScore.getPlayer().getName(),
                        firstPlayerScore.getSets(),
                        secondPlayerScore.getSets());

                req.setAttribute("matchFinishedView", matchFinishedView);

                req.getRequestDispatcher("pages/match-finished.jsp").forward(req, resp);
            } else {

                try {
                    playerScoreDAO.update(activeMatch.getFirstPlayerScore());
                    playerScoreDAO.update(activeMatch.getSecondPlayerScore());
                } catch (Exception e) {
                    throw new InternalServerException(ExceptionMessages.DATA_BASE_ERROR(), e);
                }

                resp.sendRedirect(String.format("/match-score?uuid=%s", matchId));
            }
        } else {
            throw new NotFoundException("There is no one match with that id");
        }
    }
}
