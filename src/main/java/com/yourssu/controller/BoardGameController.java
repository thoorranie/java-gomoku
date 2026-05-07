package com.yourssu.controller;

import com.yourssu.model.GomokuGame;
import com.yourssu.model.dto.GameStatusDto; // 추가된 DTO 임포트
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
        while (true) {
            GameStatusDto status = game.getStatusSnapshot();

            outputView.printTurn(status.turn());
            outputView.printBoard(status.board());
            outputView.printCurrentPlayer(status.currentPlayer());

            CoordinateDTO input = inputView.getInputForCoordinate();

            if (input == null) {
                outputView.printGameOverMessage();
                break;
            }

            int row = input.row();
            int column = input.column();

            if (game.placePiece(row, column)) {
                Symbol winnerSymbol = Symbol.of(status.currentPlayer());
                outputView.printWinner(status.board(), winnerSymbol);
                break;
            }

            if (game.isDraw()) {
                outputView.printGameOverMessage();
                break;
            }

            game.nextTurn();
        }
    }
}