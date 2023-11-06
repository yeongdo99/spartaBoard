package com.sparta.board.entity;

import com.sparta.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "board")
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pwd", nullable = false)
    private String pwd;


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "username", nullable = false)
    private String username;

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.pwd = requestDto.getPwd();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
    }
}
