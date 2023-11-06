package com.sparta.board.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto {
    private String pwd;
    private String title;
    private String contents;
    private String username;

    public BoardRequestDto(String pwd, String title, String contents, String username) {
        this.pwd = pwd;
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}

