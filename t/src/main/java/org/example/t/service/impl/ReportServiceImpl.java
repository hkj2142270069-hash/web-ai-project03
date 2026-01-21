package org.example.t.service.impl;

import org.example.t.mapper.EmpMapper;
import org.example.t.mapper.StudentsMapper;
import org.example.t.pojo.JobOption;
import org.example.t.pojo.StudentCountData;
import org.example.t.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentsMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }
    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    // ReportServiceImpl.java
    // ReportServiceImpl.java
    @Override
    public StudentCountData getStudentCountData() {

        List<Map<String, Object>> list = studentMapper.countStudentByClazz();

        List<String> clazzList = list.stream()
                .map(data -> (String) data.get("clazz"))
                .toList();

        List<Integer> dataList = list.stream()
                .map(data -> ((Long) data.get("total")).intValue())
                .toList();

        StudentCountData data = new StudentCountData();
        data.setClazzList(clazzList);
        data.setDataList(dataList);

        return data;
    }

    // ReportServiceImpl.java
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> list = studentMapper.countStudentByDegree();

        // 转换数据类型（Long -> Integer）
        return list.stream()
                .map(data -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("name", data.get("name"));
                    item.put("value", ((Long) data.get("value")).intValue());
                    return item;
                })
                .toList();
    }

}
