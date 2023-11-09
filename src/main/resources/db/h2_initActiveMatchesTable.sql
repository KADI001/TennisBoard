CREATE TABLE Active_matches (
    id VARCHAR(36) DEFAULT (uuid()) PRIMARY KEY,
    first_player_score_id INT NOT NULL,
    second_player_score_id INT NOT NULL,
    winner_player_score_id INT,

    CONSTRAINT fk_active_matches_first_player_score_id FOREIGN KEY (first_player_score_id) REFERENCES player_score(id),
    CONSTRAINT fk_active_matches_second_player_score_id FOREIGN KEY (second_player_score_id) REFERENCES player_score(id),
    CONSTRAINT fk_active_matches_winner_player_score_id FOREIGN KEY (winner_player_score_id) REFERENCES player_score(id)
);

CREATE TABLE Player_score (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT NOT NULL,
    scores INT DEFAULT 0,
    games INT DEFAULT 0,
    sets INT DEFAULT 0
);