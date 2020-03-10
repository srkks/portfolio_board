package com.rest.controller;

import com.rest.domain.Board;

import com.rest.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public Page<Board> getList(Pageable pageable, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
/*        if (page < 0) {
            page = 0;
        }
        if (size > 50) {
            size = 10;
        }*/
        PageRequest request = PageRequest.of(page, size, Sort.Direction.DESC, "boardIdx");
        return boardService.getList(request);
    }

    //@GetMapping("/list")
    public Page<Board> list(@PageableDefault Pageable pageable) {
        return boardService.findBoardList(pageable);
    }

    @GetMapping("/detail/{boardIdx}")
    public Board getDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
        Board board = boardService.getDetail(boardIdx);
        return board;
    }


    @PostMapping("/write")
    public String write(@RequestBody Board board) throws Exception {
        if (boardService.write(board) == "Saved") {
            return "Saved";
        }
        return "Error";
    }

    @DeleteMapping("/delete/{boardIdx}")
    public String delete(@PathVariable("boardIdx") int boardIdx) {
        if (boardService.delete(boardIdx) == "Deleted") {
            return "Deleted";
        }
        return "Error";
    }

    @PutMapping("/modify/{boardIdx}")
    public String modify(@PathVariable("boardIdx") int boardIdx, @RequestBody Board board) {
        if (boardService.modify(boardIdx, board) == "Modified") {
            return "Modified";
        }
        return "Error";
    }

/*    @GetMapping("/getNum")
    public int getNum(){
        return boardService.getNum();
    }*/
}
