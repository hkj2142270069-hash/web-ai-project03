package org.example.t.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.t.mapper.StudentsMapper;
import org.example.t.pojo.*;
import org.example.t.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentsServicelmp implements StudentsService {
    @Autowired
    private StudentsMapper studentsMapper;



    @Override
    public PageResult<Student> page(StudentsQueryParam studentsQueryParam) {
        //1.设置分页参数（PageHelper）
        PageHelper.startPage(studentsQueryParam.getPage(),studentsQueryParam.getPageSize());
        //2.执行查询
        List<Student> studentsList= studentsMapper.list(studentsQueryParam);
        Page< Student> p=(Page< Student>) studentsList;
        //3. 解析查询结果并封装
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentsMapper.add(student);

    }

    @Override
    public Student getInfo(Integer id) {

        return studentsMapper.getInfo(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentsMapper.update(student);

    }

    @Override
    public void delete(Integer[] ids) {
        studentsMapper.delete(ids);

    }
    @Override
    /**
     * 执行违纪处理
     * @param id 学员ID
     * @param score 扣分值
     */
    public void violation(Integer id, Integer score) {
        // 查询学员信息
        Student student = studentsMapper.getInfo(id);

        if (student == null) {
            throw new IllegalArgumentException("学员不存在");
        }

        // 验证扣分次数限制
        if (student.getViolationCount() >= 255) {
            throw new DataIntegrityViolationException( "扣分次数已达到上限（255次）");
        }

        // 验证总扣分值限制
        int totalScore = student.getViolationScore() + score;
        if (totalScore > 255) {
            throw new NumberFormatException( "累计扣分值已超过上限（255分）");
        }

        // 更新违纪次数和扣分
        student.setViolationCount((short) (student.getViolationCount() + 1));
        student.setViolationScore((short) totalScore);

        // 更新数据库
        studentsMapper.update(student);
    }
}
