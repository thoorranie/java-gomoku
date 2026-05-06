package com.yourssu.model;

import com.yourssu.view.dto.CoordinateDTO;

public class GomokuGame {
    private final GomokuBoard board = new GomokuBoard();
    private int turn = 1;
    private Piece currentPlayer = Piece.BLACK;

    public int turn() {
        return turn;
    }

    public Piece currentPlayer() {
        return currentPlayer;
    }

    public Board board() {
        return board;
    }

    public boolean isDraw() {
        return board.isFull();
    }

    public boolean placePiece(CoordinateDTO coordinate) {
        int row = coordinate.row();
        int column = coordinate.column();
        board.placePiece(row, column, currentPlayer);
        WinChecker winChecker = new WinChecker();
        return winChecker.isWin(board, row, column, currentPlayer);
    }

    public void nextTurn() {
        turn++;
        changePlayer();
    }

    private void changePlayer() {
        if (currentPlayer == Piece.BLACK) {
            currentPlayer = Piece.WHITE;
            return;
        }
        currentPlayer = Piece.BLACK;
    }
}