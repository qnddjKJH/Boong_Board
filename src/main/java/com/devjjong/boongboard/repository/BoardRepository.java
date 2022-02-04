package com.devjjong.boongboard.repository;

import com.devjjong.boongboard.domain.Board;
import com.devjjong.boongboard.domain.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager entityManager;

    public void save(Board board) {
        entityManager.persist(board);
    }

    public Board findOne(BoardType boardType) {
        return entityManager.find(Board.class, boardType);
    }

    public List<Board> findAll() {
        List<Board> boards = entityManager.createQuery("select b from Board b", Board.class)
                .getResultList();
        return boards;
    }
}
