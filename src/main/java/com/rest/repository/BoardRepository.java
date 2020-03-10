package com.rest.repository;

import com.rest.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Page<Board> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Board b set b.hitCnt = b.hitCnt+1 where b.boardIdx = :boardIdx")
    void updateHitCount(@Param("boardIdx") int boardIdx);

    Board findByBoardIdx(int boardIdx);

    @Modifying
    @Transactional
    @Query(value="update Board b set b.updatedDatetime = :#{#board.updatedDatetime}, b.updatorId = :#{#board.updatorId}," +
            " b.title = :#{#board.title}, b.contents=:#{#board.contents} where b.boardIdx = :boardIdx")
    void modify(@Param("boardIdx") int boardIdx, @Param("board") Board board);

    //int findTopByBoardIdx();
}
