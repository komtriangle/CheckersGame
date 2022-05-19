package com.example.checkers.Models;

public class GameResult {
    private  int userId;
    private  String player1Name;
    private  String player2Name;
    private  byte winner;

    public GameResult(int userId, String player1Name, String player2Name, byte winner) {
        this.userId = userId;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.winner = winner;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public byte getWinner() {
        return winner;
    }

    public void setWinner(byte winner) {
        this.winner = winner;
    }
}
