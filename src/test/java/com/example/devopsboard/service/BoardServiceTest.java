package com.example.devopsboard.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.devopsboard.domain.entity.Board;
import com.example.devopsboard.domain.repository.BoardRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
// @SpringBootTest // 스프링을 실행 시킴
class BoardServiceTest {
    // @Autowired // Bean 에서 빼옴(Spring 을 실행 시킬 때만 씀)
    @InjectMocks
    private BoardServiceImpl boardService ;

    // @Autowired
    @Mock
    private BoardRepository boardRepository;

    //      deleteById(id)
    @Test
    void deleteByIdSuccess() {
        //  case 2: id가 있을 경우
        // given -> boardRepository.deleteById(???) 이걸 주는 곳임
        // Board save = boardRepository.save(new Board(null, "test", "test"));
        // Long id = save.getId(); // 저장한 Board 의 id를 삭제시키기 위함
        Long id = 1L;
        BDDMockito.doNothing().when(boardRepository).deleteById(id);
        BDDMockito.given(boardRepository.findById(id))
            .willReturn(Optional.of(new Board(1L,null, null)));

        // when
        boardService.deleteById(id);

        // then
        // assertEquals(0, boardRepository.findAll().size());

    }

    @Test
    void deleteByIdFail() {
        //  case 1: id가 없을 경우
        // given
        Long id = 5000L;

        BDDMockito.given(boardRepository.findById(id))
            .willReturn(Optional.empty());


        // when & then -> 실행을 시키자마자 에러가 튀어나와야하기 때문에
        assertThrows(IllegalArgumentException.class, () -> boardService.deleteById(id));

    }

}