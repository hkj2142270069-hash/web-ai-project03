package org.example.t.pojo;

import lombok.Data;

@Data
public class StudentsQueryParam {
    private String no;
    private Integer page=1;
    private Integer pageSize=10;
    private String name;
    private Integer degree;
    private Integer clazzId;

}
