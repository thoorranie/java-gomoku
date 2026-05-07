package com.yourssu.model.dto;

import com.yourssu.model.Board;
import com.yourssu.model.Piece;

public record GameStatusDto(
        int turn,
        Board board,
        Piece currentPlayer
) {
}