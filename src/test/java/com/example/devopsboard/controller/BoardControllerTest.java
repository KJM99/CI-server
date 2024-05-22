package com.example.devopsboard.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.devopsboard.domain.dto.request.BoardRequest;
import com.example.devopsboard.domain.dto.response.BoardResponse;
import com.example.devopsboard.domain.entity.Board;
import com.example.devopsboard.service.BoardService;
import com.example.devopsboard.service.BoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// 내가 실습한 코드
@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BoardService boardService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test // 성공
    @DisplayName("Boards 전체 가져오기 테스트")
    void getBoards() throws Exception {
        given(boardService.getBoard()).willReturn(
            List.of(new BoardResponse("name1", "text1"),
                new BoardResponse("name2", "text2"),
                new BoardResponse("name3", "text3"))
        );

        mockMvc.perform(get("/api/v1/boards"))
            .andExpect(jsonPath("$[0].name").value("name1"))
            .andExpect(jsonPath("$[1].name").value("name2"))
            .andExpect(jsonPath("$[0].text").value("text1"))
            .andExpect(jsonPath("$.size()").value(3))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test // 성공
    @DisplayName("Board 하나 가져오기 테스트")
    void getBoard() throws Exception {
        given(boardService.getById(1L)).willReturn(new BoardResponse("name", "text"));

        String boardId = "1";

        mockMvc.perform(get("/api/v1/boards/" + boardId))
            .andExpect(jsonPath("$.name").value("name"))
            .andExpect(jsonPath("$.text").value("text"))
            .andDo(print());

    }

    @Test
    @DisplayName("Board 추가 테스트")
    void addBoard() throws Exception {

        BoardRequest board = new BoardRequest("name", "text");

        boardService.addBoard(board);

        mockMvc.perform(post("/api/v1/boards")
                .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(board))).andExpect(status().isOk()).andDo(print());
    }
}

// 강사님이랑 짠 코드
/*
@WebMvcTest(BoardController.class)
class BoardControllerTest {
    @MockBean
    private BoardServiceImpl boardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllBoards() throws Exception {
        // given
        // boardService.getAll() list[3]
        // TDD? Test Driven Design
        // DDD? Domain Driven Design
        // BDD? Business Driven Design
        // monolithic? 하나로 묶어서 개발하는 것. 코드가 길어짐
        // MSA? Service 로 나누는 것. 코드는 monolithic 보다는 길어짐
        // EDA? Event Driven Architecture -> 데이터 분석에서 많이 씀
        BDDMockito.given(boardService.getBoard()).willReturn(List.of(
            new BoardResponse("test1", "test1"),
            new BoardResponse("test2", "test2"),
            new BoardResponse("test3", "test3")
        ));

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/boards"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("test1")) // 검증
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3)) // 검증
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print()); // 하는거
    }
}*/