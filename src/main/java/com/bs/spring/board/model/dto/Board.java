package com.bs.spring.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private Long boardNo;
    private String boardTitle;
    private String boardWriter;
    private String boardContent;
    private Date boardDate;
    private Integer boardReadCount;
    private List<Attachment> files;
}
