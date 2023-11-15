CREATE TABLE Matches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_player_id INT NOT NULL,
    second_player_id INT NOT NULL,
    winner_player_id INT NOT NULL,

    CONSTRAINT fk_matches_winner_first_player_id FOREIGN KEY (first_player_id) REFERENCES Players(id),
    CONSTRAINT fk_matches_winner_second_player_id FOREIGN KEY (second_player_id) REFERENCES Players(id),
    CONSTRAINT fk_matches_winner_player_id FOREIGN KEY (winner_player_id) REFERENCES Players(id)
);