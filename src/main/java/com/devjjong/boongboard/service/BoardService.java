package com.devjjong.boongboard.service;

import com.devjjong.boongboard.domain.Board;
import com.devjjong.boongboard.domain.BoardType;
import com.devjjong.boongboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardType save(Board board) {
        boardRepository.save(board);
        return board.getBoardType();
    }

    public Board findOne(BoardType boardType) {
        return boardRepository.findOne(boardType);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
