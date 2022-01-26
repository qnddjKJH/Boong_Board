package com.devjjong.boongboard.dto;

import com.devjjong.boongboard.domain.BoardType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class PostForm {

    @NotEmpty(message = "제목은 필수 입니다.")
    private String title;

    @NotEmpty(message = "내용은 필수 입니다.")
    private String content;

    private LocalDateTime regDate;

    private LocalDateTime editDate;

    private BoardType boardType;
}
