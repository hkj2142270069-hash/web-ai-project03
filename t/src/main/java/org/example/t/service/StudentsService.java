package org.example.t.service;

import org.example.t.pojo.PageResult;
import org.example.t.pojo.Student;
import org.example.t.pojo.StudentsQueryParam;

public interface StudentsService {
    PageResult<Student> page(StudentsQueryParam studentsQueryParam);

    void add(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void delete(Integer[] ids);

    void violation(Integer id, Integer score);
}
