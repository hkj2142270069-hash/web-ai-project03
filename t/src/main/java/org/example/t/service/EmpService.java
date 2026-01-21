package org.example.t.service;

import org.example.t.pojo.Emp;
import org.example.t.pojo.EmpQueryParam;
import org.example.t.pojo.LoginInfo;
import org.example.t.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

//分页查询
public interface EmpService {
   // PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);



    void deleteByIds(List<Integer> ids);

    Emp getInto(Integer id);

    void update(Emp emp);


    List<Emp> listAll();

    LoginInfo login(Emp emp);
}
