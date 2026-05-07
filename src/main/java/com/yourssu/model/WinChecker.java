package com.yourssu.model;

public class WinChecker {

    public boolean isWin(Board board, int row, int column, Piece piece) {
        for (Direction direction : Direction.values()) {
            if (hasFiveStones(board, row, column, piece, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasFiveStones(Board board, int row, int column, Piece piece, Direction direction) {
        int dr = direction.rowDelta();
        int dc = direction.columnDelta();

        int forward = countStones(board, row, column, piece, dr, dc);
        int backward = countStones(board, row, column, piece, -dr, -dc);

        return (1 + forward + backward) >= 5;
    }

    private int countStones(Board board, int row, int column, Piece piece, int dr, int dc) {
        int count = 0;
        int currentRow = row + dr;
        int currentCol = column + dc;

        while (!isOutOfBounds(currentRow, currentCol)) {
            if (board.getPiece(currentRow, currentCol) != piece) {
                break;
            }
            count++;
            currentRow += dr;
            currentCol += dc;
        }
        return count;
    }

    private boolean isOutOfBounds(int row, int column) {
        return row < 0 || row >= GomokuBoard.SIZE ||
                column < 0 || column >= GomokuBoard.SIZE;
    }
}