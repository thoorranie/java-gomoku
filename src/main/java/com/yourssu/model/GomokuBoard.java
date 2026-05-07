package com.yourssu.model;

public class GomokuBoard implements Board {
    public static final int SIZE = 15;
    private final Piece[][] grid = new Piece[SIZE][SIZE];
    private int pieceCount = 0;

    public GomokuBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public Piece getPiece(int row, int column) {
        return grid[row][column];
    }

    @Override
    public void placePiece(int row, int column, Piece piece) {
        validateEmpty(row, column);
        grid[row][column] = piece;
        pieceCount++;
    }

    private void validateEmpty(int row, int column) {
        if (grid[row][column] != Piece.EMPTY) {
            throw new IllegalArgumentException("이미 돌이 놓여 있는 자리입니다.");
        }
    }

    public boolean isFull() {
        return pieceCount == (SIZE * SIZE);
    }
}