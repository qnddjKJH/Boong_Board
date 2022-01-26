package com.devjjong.boongboard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter @Setter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private LocalDateTime regDate;

    private LocalDateTime editDate;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;
}
