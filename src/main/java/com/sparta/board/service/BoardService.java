package com.sparta.board.service;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        Board saveBoard = boardRepository.save(board);
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);
        return boardResponseDto;
    }

    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    public  BoardResponseDto getBoard(Long id) {
        Board board = findBoard(id);
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, String pwd, BoardRequestDto requestDto) {
        // DB에 존재하는 지 확인
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!board.getPwd().equals(pwd)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        board.update(requestDto);
        return new BoardResponseDto(board);
    }
    
    public Long deleteBoard(Long id, String pwd) {
        // DB에 존재하는 지 확인
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!board.getPwd().equals(pwd)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        boardRepository.delete(board);
        return id;
    }


    public Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }
}
