package org.example.t.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.t.mapper.EmpExprMapper;
import org.example.t.mapper.EmpMapper;
import org.example.t.pojo.*;
import org.example.t.service.EmpLogService;
import org.example.t.service.EmpService;
import org.example.t.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpSeriviceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    //原始代码
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.调用mapper查询数据
//        Long count = empMapper.count();
//        //2.调用mapper查询结果列表
//        List<Emp> rows=empMapper.list((page-1)*pageSize,pageSize);
//        //3.封装PageResult对象
//        return new PageResult<Emp>(count,rows);
//    }
        @Override
    public PageResult<Emp> page( EmpQueryParam empQueryParam) {
            //1.设置分页参数（PageHelper）
            PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
            //2.执行查询
            List<Emp> empList = empMapper.list(empQueryParam);
            Page<Emp> p = (Page<Emp>) empList;
            //3. 解析查询结果并封装
            return new PageResult<Emp>(p.getTotal(), p.getResult());


    }

    @Transactional(rollbackFor = {Exception.class}) //开启事务
    @Override
    public void save(Emp emp) {
        //1.保存员工基本信息（补全基础属性）
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.保存员工经历信息
            List<EmpExpr> exprList=emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合为empId赋值
                exprList.forEach( empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"保存员工信息："+emp);
            empLogService.insertLog(empLog);

        }

    }
    @Transactional (rollbackFor = {Exception.class})
    @Override
    public void deleteByIds(List<Integer> ids) {
        //1. 根据ID批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2. 根据员工的ID批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }
    @Override
    public Emp getInto(Integer id) {
       return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
            //1. 修改员工基本信息
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.updateById(emp);
            empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //3. 新增员工的工作经历数据 【新增新的】
            Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }
    @Override
    public List<Emp> listAll() {
        return empMapper.selectAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.selectUsernameAndPassword(emp);
        if(empLogin != null){
            //1. 生成JWT令牌
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());

            String jwt = JwtUtils.generateJwt(dataMap);
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }
        return null;
    }
}
