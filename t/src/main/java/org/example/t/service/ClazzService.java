package org.example.t.service;

import org.example.t.pojo.Clazz;
import org.example.t.pojo.ClazzQueryParam;
import org.example.t.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);


    List<Clazz> getAllClazzs();

    void add(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void deleteClazz(Integer id);


}
