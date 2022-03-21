package com.chany.blog.controller.api;

import com.chany.blog.config.auth.PrincipalDetail;
import com.chany.blog.dto.ResponseDto;
import com.chany.blog.model.Board;
import com.chany.blog.model.Reply;
import com.chany.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board,
                                     @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.write(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> delete(@PathVariable Integer id) {
        boardService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Integer id,
                                       @RequestBody Board board) {
        boardService.update(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // /api/board/${data.boardId}/reply

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable int boardId,
                                          @RequestBody Reply reply,
                                          @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.writeReply(reply, boardId, principal.getUser());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
