package com.example.devopsboard.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.devopsboard.domain.entity.Board;
import com.example.devopsboard.domain.repository.BoardRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 스프링을 실행 시킴
class BoardServiceTest {
    @Autowired // Bean 에서 빼옴
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    //      deleteById(id)
    @Test
    void deleteByIdSuccess() {
        //  case 2: id가 있을 경우
        // given -> boardRepository.deleteById(???) 이걸 주는 곳임
        Board save = boardRepository.save(new Board(null, "test", "test"));
        Long id = save.getId(); // 저장한 Board 의 id를 삭제시키기 위함

        // when
        boardService.deleteById(id);

        // then
        assertEquals(0, boardRepository.findAll().size());
    }

    @Test
    void deleteByIdFail() {
        //  case 1: id가 없을 경우
        // given
        Long id = 80000L;

        // when & then -> 실행을 시키자마자 에러가 튀어나와야하기 때문에
        assertThrows(IllegalArgumentException.class, () -> boardService.deleteById(id));

    }

}