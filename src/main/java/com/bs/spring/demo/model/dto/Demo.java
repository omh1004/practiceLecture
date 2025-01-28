package com.bs.spring.demo.model.dto;

import lombok.*;

//import java.util.Date
import javax.validation.constraints.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demo {
    @NotEmpty(message = "이름은 반드시 작성하세요!")
    @Pattern(regexp = "[가-힣]{2,}" , message = "두 글자 이상 한글만 사용가능합니다. ")
    private String devName;
    @Min(value=14 ,message = "애들은 가라")
    private int devAge;
    private String devGender;
    private String[] devLang;
    private Address address;
    @NotNull(message="생년월일은 반드시 작성하세요 ")
    @Past(message = "미래에서 왔구나 !")
    private Date birthday;
    @Email
    private String devEmail;
}
