package com.example.devopsboard.domain.dto.response;

import com.example.devopsboard.domain.entity.Board;

public record BoardResponse(
    String name, String text
) {
    public static BoardResponse from(Board board) {
        return new BoardResponse(board.getName(), board.getText());
    }
}
