package com.bs.spring.memo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Memo {
    private String memoNo;
    private String memo;
    private String password;
    private Date memoDate;


}
