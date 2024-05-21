package com.example.devopsboard.service;

import com.example.devopsboard.domain.dto.request.BoardRequest;
import com.example.devopsboard.domain.dto.response.BoardResponse;
import java.util.List;


public interface BoardService {

    List<BoardResponse> getBoard();

    void addBoard(BoardRequest req);

    BoardResponse getById(Long id);
}
