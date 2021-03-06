package com.devjjong.boongboard.controller;

import com.devjjong.boongboard.domain.Board;
import com.devjjong.boongboard.domain.BoardType;
import com.devjjong.boongboard.domain.Post;
import com.devjjong.boongboard.dto.PostForm;
import com.devjjong.boongboard.service.BoardService;
import com.devjjong.boongboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final BoardService boardService;
    private final PostService postService;


    @GetMapping
    public String home(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/postList";
    }

    @GetMapping("/{postId}")
    public String postInfo(@PathVariable Long postId, Model model) {
        PostForm form = getPostForm(postId);

        model.addAttribute("postForm", form);
        return "posts/post";
    }

    @GetMapping("/post")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("boards", boardService.findAll());
        return "posts/createPostForm";
    }

    @PostMapping("/post")
    public String createPost(@Valid PostForm form, BindingResult result, RedirectAttributes redirectAttributes) {
        log.info("formData={}", form.getBoardType());
        if (result.hasErrors()) {
            return "posts/createPostForm";
        }

        Board board = boardService.findOne(form.getBoardType());

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        LocalDateTime now = LocalDateTime.now();
        post.setRegDate(now);
        post.setEditDate(now);
        post.setBoard(board);

        Long savedId = postService.savePost(post);

        redirectAttributes.addAttribute("savedId", savedId);

        return "redirect:/posts/{savedId}";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        PostForm postForm = getPostForm(postId);

        model.addAttribute("postForm", postForm);
        return "posts/updatePostForm";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, @Valid PostForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/posts/{postId}/edit";
        }
        postService.updatePost(postId, form);
        return "redirect:/posts/{postId}";
    }

    private PostForm getPostForm(Long postId) {
        Post post = postService.findById(postId);
        PostForm form = new PostForm();
        form.setId(postId);
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());
        form.setRegDate(post.getRegDate());
        form.setEditDate(post.getEditDate());
        return form;
    }

    // ????????? DELETE Mapping ?????? ??????
    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }


    /** ???????????? ????????? ?????? */
    @PostConstruct
    public void init() {
        Board board1 = new Board();
        board1.setBoardType(BoardType.free);

        Board board2 = new Board();
        board2.setBoardType(BoardType.notice);

        boardService.save(board1);
        boardService.save(board2);

        String textForTest = "This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.";
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < 5; i++) {
            Post post = new Post();
            post.setTitle("Title :: " + (i + 1));
            post.setContent(textForTest);
            post.setRegDate(now);
            post.setEditDate(now);
            if (i % 2 == 0) {
                post.setBoard(board1);
            } else {
                post.setBoard(board2);
            }
            postService.savePost(post);
        }
    }

}
