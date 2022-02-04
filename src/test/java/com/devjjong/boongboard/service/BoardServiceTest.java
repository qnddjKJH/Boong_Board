package com.devjjong.boongboard.service;

import com.devjjong.boongboard.domain.Board;
import com.devjjong.boongboard.domain.BoardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    public void boardTest() throws Exception {
        // given
        Board board = new Board();
        board.setBoardType(BoardType.notice);

        // when
        boardService.save(board);
        List<Board> boards = boardService.findAll();

        // then
        assertThat(boards.size()).isEqualTo(1);

    }


}