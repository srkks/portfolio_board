package com.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardIdx;

    private String title;

    private String contents;

    private Integer hitCnt = 0;

    @Column(nullable = false)
    private LocalDateTime createdDatetime = LocalDateTime.now();

    private String creatorId;

    private LocalDateTime updatedDatetime;

    private String updatorId;

/*    @Column(nullable=false, columnDefinition = "default N")
    private String deletedYn;*/
}
