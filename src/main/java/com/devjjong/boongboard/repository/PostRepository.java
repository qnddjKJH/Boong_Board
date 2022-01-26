package com.devjjong.boongboard.repository;

import com.devjjong.boongboard.domain.BoardType;
import com.devjjong.boongboard.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager entityManager;

    public void save(Post post) {
        entityManager.persist(post);
    }

    public void delete(Long id) {
        Post post = entityManager.find(Post.class, id);
        entityManager.remove(post);
    }

    public Post findById(Long id) {
        Post findPost = entityManager.find(Post.class, id);
        return findPost;
    }

    public List<Post> findAll() {
        List<Post> posts = entityManager.createQuery("select p from Post p", Post.class)
                .getResultList();
        return posts;
    }

    public List<Post> findAllByBoardType(BoardType boardType) {
        List<Post> posts = entityManager.createQuery("select p from Post p where p.boardType = :boardType", Post.class)
                .setParameter("boardType", boardType)
                .getResultList();
        return posts;
    }

}
