package com.devjjong.boongboard.service;


import com.devjjong.boongboard.domain.Post;
import com.devjjong.boongboard.dto.PostForm;
import com.devjjong.boongboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long savePost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.delete(id);
    }

    @Transactional
    public void updatePost(Long id, PostForm form) {
        LocalDateTime now = LocalDateTime.now();

        Post post = postRepository.findById(id);
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setEditDate(now);
    }

    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

}
