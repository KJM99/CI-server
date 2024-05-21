package com.example.devopsboard.domain.dto.request;

import com.example.devopsboard.domain.entity.Board;

public record BoardRequest(
    String name, String text
) {

    public Board toEntity() {
        return Board.builder().name(name).text(text).build();
    }
}
