package com.bs.spring.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
    private Long attachmentNo;
    private Long boardNo;
    private String originalFileName;
    private String renamedFileName;
    private Date uploadDate;
    private int downloadCount;
}
