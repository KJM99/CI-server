package com.example.devopsboard.controller;

import com.example.devopsboard.domain.dto.request.BoardRequest;
import com.example.devopsboard.domain.dto.response.BoardResponse;
import com.example.devopsboard.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/boards")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173"
    ,methods = {
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.DELETE,
    RequestMethod.PUT,
    RequestMethod.OPTIONS})
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardResponse> getBoards() {
        return boardService.getBoard();
    }

    @GetMapping("{id}")
    public BoardResponse getBoard(@PathVariable long id) {
        return boardService.getById(id);
    }

    @PostMapping
    public void addBoard(@RequestBody BoardRequest req){
        boardService.addBoard(req);
    }
}
