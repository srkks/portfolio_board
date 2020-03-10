package com.rest.service;

import com.rest.domain.Board;
import com.rest.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Page<Board> getList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Page<Board> findBoardList(Pageable pageable) {
        // pageable로 넘어온 pageNumber 객체가 0 이하일 때 0으로 초기화. 기본 페이지 크기인 10으로 새로운
        // PageRequest 객체를 만들어 페이징 처리된 게시글 리스트 반환
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board getDetail(int boardIdx) {
        boardRepository.updateHitCount(boardIdx);
        return boardRepository.findByBoardIdx(boardIdx);
    }

    @Override
    public String write(Board board) {
        boardRepository.save(board);
        return "Saved";
    }

    @Override
    public String delete(int boardIdx) {
        boardRepository.deleteById(boardIdx);
        return "Deleted";
    }

    @Override
    public String modify(int boardIdx, Board board) {
        board.setUpdatedDatetime(LocalDateTime.now());
        //log.info("BoardService.board = " + board);
        boardRepository.modify(boardIdx, board);
        return "Modified";
    }

/*    @Override
    public int getNum(){
        return boardRepository.findTopByBoardIdx();
    }*/
}

