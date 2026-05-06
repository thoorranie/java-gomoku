package com.yourssu.controller;

import com.yourssu.model.Board;
import com.yourssu.model.GomokuGame;
import com.yourssu.model.Piece;
import com.yourssu.view.InputView;
import com.yourssu.view.OutputView;
import com.yourssu.view.dto.CoordinateDTO;
import com.yourssu.view.implement.ConsoleInputView;
import com.yourssu.view.implement.ConsoleOutputView;
import com.yourssu.view.implement.Symbol;

public class BoardGameController implements Controller {
    private final InputView inputView = new ConsoleInputView();
    private final OutputView outputView = new ConsoleOutputView();
    private final GomokuGame game = new GomokuGame();

    @Override
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = executeTurn();
        }
    }

    private boolean executeTurn() {
        printTurnStatus();
        CoordinateDTO input = inputView.getInputForCoordinate();
        return processInput(input);
    }

    private void printTurnStatus() {
        int turn = game.turn();
        outputView.printTurn(turn);
        Board board = game.board();
        outputView.printBoard(board);
        Piece player = game.currentPlayer();
        outputView.printCurrentPlayer(player);
    }

    private boolean processInput(CoordinateDTO input) {
        if (input == null) {
            outputView.printGameOverMessage();
            return false;
        }
        return handleMove(input);
    }

    private boolean handleMove(CoordinateDTO input) {
        boolean isWin = game.placePiece(input);
        if (isWin) {
            handleWin();
            return false;
        }
        return handleDraw();
    }

    private boolean handleDraw() {
        boolean isDraw = game.isDraw();
        if (isDraw) {
            outputView.printGameOverMessage();
            return false;
        }
        return continueGame();
    }

    private void handleWin() {
        Piece player = game.currentPlayer();
        Symbol winnerSymbol = Symbol.of(player);
        Board board = game.board();
        outputView.printWinner(board, winnerSymbol);
    }

    private boolean continueGame() {
        game.nextTurn();
        return true;
    }
}