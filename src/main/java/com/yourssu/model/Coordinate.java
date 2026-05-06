package com.yourssu.model;

public record Coordinate(int row, int column) {

    public Coordinate move(int rowDelta, int columnDelta) {
        int nextRow = this.row + rowDelta;
        int nextColumn = this.column + columnDelta;
        return new Coordinate(nextRow, nextColumn);
    }
}