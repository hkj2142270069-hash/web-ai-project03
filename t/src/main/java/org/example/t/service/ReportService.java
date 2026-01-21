package org.example.t.service;

import org.example.t.pojo.JobOption;
import org.example.t.pojo.StudentCountData;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();
    /**
     * 统计员工性别信息
     */
    List<Map> getEmpGenderData();

    StudentCountData getStudentCountData();
    /**
     * 统计学生信息
     */
    List<Map<String, Object>> getStudentDegreeData();
}

