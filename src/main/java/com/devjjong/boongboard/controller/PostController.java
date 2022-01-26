package com.devjjong.boongboard.controller;

import com.devjjong.boongboard.domain.BoardType;
import com.devjjong.boongboard.domain.Post;
import com.devjjong.boongboard.dto.PostForm;
import com.devjjong.boongboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/postList";
    }

    @GetMapping("/{postId}")
    public String postInfo(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId);
        PostForm form = new PostForm();
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());
        form.setRegDate(post.getRegDate());
        form.setEditDate(post.getEditDate());

        model.addAttribute("postForm", form);
        return "posts/post";
    }

    @GetMapping("/post")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "posts/postForm";
    }

    @PostMapping("/post")
    public String createPost(@Valid PostForm form, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "posts/postForm";
        }

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        LocalDateTime now = LocalDateTime.now();
        post.setRegDate(now);
        post.setEditDate(now);
        post.setBoardType(BoardType.free);

        Long savedId = postService.savePost(post);

        redirectAttributes.addAttribute("savedId", savedId);

        return "redirect:/{savedId}";
    }

    /** 테스트용 데이터 추가 */
    @PostConstruct
    public void init() {
        String textForTest = "This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.";
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < 5; i++) {
            Post post = new Post();
            post.setTitle("Title :: " + (i + 1));
            post.setContent(textForTest);
            post.setRegDate(now);
            post.setEditDate(now);
            post.setBoardType(BoardType.free);
            postService.savePost(post);
        }

    }

}
