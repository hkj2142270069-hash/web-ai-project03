package org.example.t.service;

import org.example.t.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询所有的部门数据
    List<Dept> findAll();

    void deleteById(Integer id);

    void add(Dept dept);

    Dept getInto(Integer id);

    void update(Dept dept);
}
