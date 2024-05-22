package com.example.devopsboard.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.example.devopsboard.domain.repository.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardTest {

    @Test
    void getId() {
        Board b1 = new Board(1L, "test", "test");
        Board b2 = new Board(1L, "test", "test");
        Board b3 = new Board(2L, "test2", "test2");

        assertEquals(b1, b2);
        assertNotEquals(b1, b3);

        assertEquals(b1.hashCode(), b2.hashCode());
        assertNotEquals(b1.hashCode(), b3.hashCode());

        Long id = 3L;
        String name = "Board1";
        String text = "This is board 1";

        // When
        Board board = Board.builder()
            .id(id)
            .name(name)
            .text(text)
            .build();

        // Then
        assertEquals(id, board.getId(), "The id should match the one set by the builder");
        assertEquals(name, board.getName(), "The name should match the one set by the builder");
        assertEquals(text, board.getText());

    }
}