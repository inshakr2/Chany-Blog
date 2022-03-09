package com.chany.blog.controller;

import com.chany.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // @AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"", "/"})
    public String index(Model model,
                        @PageableDefault(size=3, sort="id", direction = DESC) Pageable pageable) {

        model.addAttribute("boards", boardService.list(pageable));
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }


}
