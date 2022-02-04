package com.devjjong.boongboard.service;

import com.devjjong.boongboard.domain.BoardType;
import com.devjjong.boongboard.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired PostService postService;

    @Test
    public void saveTest() throws Exception {
        // given
        Post post = new Post();
        post.setTitle("test");
        post.setContent("test");
//        post.setBoardType(BoardType.notice);
        LocalDateTime now = LocalDateTime.now();
        post.setRegDate(now);
        post.setEditDate(now);

        // when
        Long savedId = postService.savePost(post);

        // then
        assertThat(post).isEqualTo(postService.findById(savedId));
    }
    
    @Test
    public void updateTest() throws Exception {
        // given
        Post post = new Post();
        post.setTitle("test");
        post.setContent("test");
//        post.setBoardType(BoardType.notice);
        LocalDateTime now = LocalDateTime.now();
        post.setRegDate(now);
        post.setEditDate(now);
        Long savedId = postService.savePost(post);

        // when
        now = LocalDateTime.now();
        post.setEditDate(now);
        post.setTitle("updateTest");
        
        // then
        assertThat(post.getTitle()).isEqualTo(postService.findById(savedId).getTitle());
    }

    @Test
    public void deleteTest() throws Exception {
        // given
        Post post = new Post();
        post.setTitle("test");
        post.setContent("test");
//        post.setBoardType(BoardType.notice);
        LocalDateTime now = LocalDateTime.now();
        post.setRegDate(now);
        post.setEditDate(now);
        Long savedPost = postService.savePost(post);

        Post test = new Post();
        test.setTitle("deleteTest");
        postService.savePost(test);

        // when
        postService.deletePost(savedPost);

        // then
        assertThat(postService.findAll().size()).isEqualTo(1);
        assertThat(postService.findById(savedPost)).isNull();
    }
}