package com.chany.blog.service;

import com.chany.blog.model.Board;
import com.chany.blog.model.Reply;
import com.chany.blog.model.User;
import com.chany.blog.repository.BoardRepository;
import com.chany.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public void write(Board board, User user) {
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> list(Pageable pageable) {

        return boardRepository.findAllWithUser(pageable);
    }

    @Transactional(readOnly = true)
    public Board detail(Integer id) {
        return boardRepository.findWithReplies(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패 : ID를 찾을 수 없습니다.");
                });
    }

    public void delete(Integer id) {
        boardRepository.deleteById(id);
    }

    public void update(Integer id, Board requestBoard) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 수정하기 실패 : ID를 찾을 수 없습니다.");
                });

        findBoard.setTitle(requestBoard.getTitle());
        findBoard.setContent(requestBoard.getContent());
    }

    public void writeReply(Reply reply, int boardId, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글쓰기 실패 : 게시판 ID를 찾을 수 없습니다.");
                });
        reply.setBoard(board);
        reply.setUser(user);
        replyRepository.save(reply);
    }
}
