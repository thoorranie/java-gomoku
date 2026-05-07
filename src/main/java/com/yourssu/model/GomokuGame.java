package com.yourssu.model;

import com.yourssu.model.dto.GameStatusDto;

public class GomokuGame {
    private final GomokuBoard board = new GomokuBoard();
    private final WinChecker winChecker = new WinChecker();

    private int turn = 1;
    private Piece currentPlayer = Piece.BLACK;

    public GameStatusDto getStatusSnapshot() {
        return new GameStatusDto(turn, board, currentPlayer);
    }

    public boolean isDraw() {
        return board.isFull();
    }

    public boolean placePiece(int row, int column) {
        board.placePiece(row, column, currentPlayer);
        return winChecker.isWin(board, row, column, currentPlayer);
    }

    public void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Piece.BLACK) ? Piece.WHITE : Piece.BLACK;
    }
}