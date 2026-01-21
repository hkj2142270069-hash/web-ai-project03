package org.example.t.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.t.exception.BusinessException;
import org.example.t.mapper.ClazzMapper;
import org.example.t.pojo.*;
import org.example.t.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServicelmpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;


    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList=clazzMapper.list(clazzQueryParam);
        Page<Clazz> p=(Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());

    }

    @Override
    public List<Clazz> getAllClazzs() {
        return clazzMapper.getAllClazzs();
    }

    @Override
    public void add(Clazz clazz) {

            clazz.setCreateTime(LocalDateTime.now());
            clazz.setUpdateTime(LocalDateTime.now());
            clazzMapper.add(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);

    }

    @Override
    public void deleteClazz(Integer id) {
        // 检查该班级下是否有学生
        int studentCount = clazzMapper.countStudentsByClazzId(id);
        if (studentCount > 0) {
            // 如果有学生，抛出自定义异常
            throw new BusinessException("对不起, 该班级下有学生, 不能直接删除");
        }
        // 执行删除操作
        clazzMapper.delete(new Integer[]{id});
    }



}
