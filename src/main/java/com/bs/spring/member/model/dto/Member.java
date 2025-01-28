package com.bs.spring.member.model.dto;


import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Min(value = 4)
    private String userId;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8}$", message = "특수문자숫자포함 8글자여야함니다. ")
    private String password;
    @Pattern(regexp="[가-힣]",message = "한글만 입력가능 ")
    private String userName;
    private String gender;
    @Min(value = 14 , message = "14~80세까지만 가능 ")
    @Max(value = 80 , message = "14~80세까지만 가능 ")
    private int age;
    private String email;
    private String phone;
    private String address;
    @NotNull
    private String[] hobby;
    private Date enrollDate;
}
