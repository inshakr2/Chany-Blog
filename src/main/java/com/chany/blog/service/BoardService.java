package com.chany.blog.service;

import com.chany.blog.model.Board;
import com.chany.blog.model.User;
import com.chany.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void write(Board board, User user) {
        board.setUser(user);
        boardRepository.save(board);
    }
}
