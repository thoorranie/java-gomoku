package com.yourssu.model;

import java.util.Arrays;
import java.util.stream.Stream;

public class WinChecker {

    public boolean isWin(Board board, int row, int column, Piece piece) {
        Coordinate coordinate = new Coordinate(row, column);
        Direction[] directions = Direction.values();
        Stream<Direction> directionStream = Arrays.stream(directions);
        return directionStream.anyMatch(direction -> hasFiveStones(board, coordinate, piece, direction));
    }

    private boolean hasFiveStones(Board board, Coordinate coordinate, Piece piece, Direction direction) {
        int rowDelta = direction.rowDelta();
        int columnDelta = direction.columnDelta();
        int forward = countStones(board, coordinate, piece, rowDelta, columnDelta);
        int backward = countStones(board, coordinate, piece, -rowDelta, -columnDelta);
        int totalStones = 1 + forward + backward;
        return totalStones >= 5;
    }

    private int countStones(Board board, Coordinate current, Piece piece, int rowDelta, int columnDelta) {
        Coordinate next = current.move(rowDelta, columnDelta);
        if (isOutOfBounds(next)) {
            return 0;
        }
        if (isDifferentPiece(board, next, piece)) {
            return 0;
        }
        return 1 + countStones(board, next, piece, rowDelta, columnDelta);
    }

    private boolean isDifferentPiece(Board board, Coordinate coordinate, Piece piece) {
        int row = coordinate.row();
        int column = coordinate.column();
        Piece currentPiece = board.getPiece(row, column);
        return currentPiece != piece;
    }

    private boolean isOutOfBounds(Coordinate coordinate) {
        if (coordinate.row() < 0) return true;
        if (coordinate.row() >= GomokuBoard.SIZE) return true;
        if (coordinate.column() < 0) return true;
        if (coordinate.column() >= GomokuBoard.SIZE) return true;
        return false;
    }
}