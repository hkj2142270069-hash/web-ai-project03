package org.example.t.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.t.pojo.JobOption;
import org.example.t.pojo.Result;
import org.example.t.pojo.StudentCountData;
import org.example.t.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    /**
     * 统计员工性别信息
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    /**
     * 统计每个班级学员
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计每个班级学员");
        StudentCountData studentCountData = reportService.getStudentCountData();
        return Result.success(studentCountData);

    }
    // ReportController.java
    /**
     * 统计学员学历信息
     */
    @GetMapping("/studentDegreeData")
    public Result  getStudentDegreeData() {
        log.info("统计学员学历信息");
        List<Map<String, Object>> degreeData = reportService.getStudentDegreeData();
        return Result.success(degreeData);
    }

}
