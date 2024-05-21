package com.example.devopsboard.service;

import com.example.devopsboard.domain.dto.request.BoardRequest;
import com.example.devopsboard.domain.dto.response.BoardResponse;
import com.example.devopsboard.domain.entity.Board;
import com.example.devopsboard.domain.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    public final BoardRepository boardRepository;

    @Override
    public List<BoardResponse> getBoard() {

        return boardRepository.findAll()
            .stream()
            .map(BoardResponse::from)
            .toList();
    }

    @Override
    public void addBoard(BoardRequest req) {
        boardRepository.save(req.toEntity());
    }

    @Override
    public BoardResponse getById(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new IllegalArgumentException("null");
        }
        return BoardResponse.from(board);
    }

    @Override
    public void deleteById(Long id) {
        // 이렇게하면 Select 문이 들어감
        Optional<Board> byId = boardRepository.findById(id);
        byId.orElseThrow(IllegalArgumentException::new);
        boardRepository.deleteById(id);

    }
}


//