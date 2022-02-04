package com.devjjong.boongboard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "boards")
@Getter @Setter
public class Board {

    @Id @Column(name = "board_id")
    @Enumerated(EnumType.STRING)
    private BoardType boardType;
}