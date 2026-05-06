package com.yourssu.model;

import java.util.HashMap;
import java.util.Map;

public class GomokuBoard implements Board {
    public static final int SIZE = 15;
    private final Map<Coordinate, Piece> grid = new HashMap<>();

    @Override
    public Piece getPiece(int row, int column) {
        Coordinate coordinate = new Coordinate(row, column);
        return grid.getOrDefault(coordinate, Piece.EMPTY);
    }

    @Override
    public void placePiece(int row, int column, Piece piece) {
        Coordinate coordinate = new Coordinate(row, column);
        validateEmpty(coordinate);
        grid.put(coordinate, piece);
    }

    private void validateEmpty(Coordinate coordinate) {
        if (grid.containsKey(coordinate)) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isFull() {
        int currentSize = grid.size();
        int maxSize = SIZE * SIZE;
        return currentSize == maxSize;
    }
}