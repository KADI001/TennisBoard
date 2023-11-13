package org.kadirov.dto;

public record MatchFinishedView(
        String firstPlayerName,
        String secondPlayerName,
        int firstPlayerSets,
        int secondPlayerSets
) {

}
