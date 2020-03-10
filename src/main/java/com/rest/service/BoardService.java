package com.rest.service;

import com.rest.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    Page<Board> getList(Pageable pageable);

    Board getDetail(int boardIdx);

    String write(Board board);

    String delete(int boardIdx);

    String modify(int boardIdx, Board board);

    //int getNum();

    Page<Board> findBoardList(Pageable pageable);
}
