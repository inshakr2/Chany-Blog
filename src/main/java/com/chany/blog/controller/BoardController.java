package com.chany.blog.controller;

import com.chany.blog.model.Board;
import com.chany.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // @AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"", "/"})
    public String index(Model model,
                        @PageableDefault(size = 5, sort = "id", direction = DESC) Pageable pageable) {

        model.addAttribute("boards", boardService.list(pageable));
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Board board = boardService.detail(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String update(@PathVariable int id, Model model) {

        Board board = boardService.detail(id);
        model.addAttribute("board", board);

        return "board/updateForm";
    }
}
