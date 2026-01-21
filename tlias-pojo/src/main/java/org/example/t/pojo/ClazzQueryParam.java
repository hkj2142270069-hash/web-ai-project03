package org.example.t.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    private Integer page;
    private Integer pageSize;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd") //格式化日期
    private LocalDate  begin; //入职日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;  //离职日期
    // Lombok的@Data注解会自动生成getter/setter方法
}
