DROP DATABASE IF EXISTS GuessTheNumberDB;
CREATE DATABASE GuessTheNumberDB;

USE GuessTheNumberDB;

CREATE TABLE Game(
	gameId INT PRIMARY KEY AUTO_INCREMENT,
	answer CHAR(4) NOT NULL,
	finished BOOLEAN DEFAULT false NOT NULL);

CREATE TABLE Round(
	roundNumber INT PRIMARY KEY AUTO_INCREMENT,
	guess INT NOT NULL,
	result CHAR(7) NOT NULL,
	time DATETIME NOT NULL,
    gameId INT NOT NULL,
    FOREIGN KEY (gameId) REFERENCES Game(gameId));