package org.example.t.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.t.pojo.*;

import org.example.t.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;
    @GetMapping
    public Result page(StudentsQueryParam studentsQueryParam){
        log.info("分页查询员工信息，参数：{}",studentsQueryParam);
        PageResult<Student> pageResult=studentsService.page(studentsQueryParam);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加员工，员工信息：{}",student);
        studentsService.add(student);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工信息");
        Student student=studentsService.getInfo(id);
        return Result.success(student);
    }
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新员工信息：{}",student);
        studentsService.update(student);
        return Result.success();
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("删除员工，员工id：{}", ids);

        // 解析逗号分隔的ID字符串
        Integer[] idArray = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        studentsService.delete(idArray);
        return Result.success();
    }
    /**
     * 违纪处理接口
     * 请求路径: /students/violation/{id}/{score}
     * 请求方式: PUT
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("执行违纪处理，学员ID：{}，扣分：{}", id, score);

        // 参数验证
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("学员ID必须大于0");
        }

        if (score == null || score < 0) {
            throw new IllegalArgumentException("扣分值必须大于等于0");
        }

        // 执行违纪处理（业务逻辑）
        studentsService.violation(id, score);
        return Result.success();
    }
}
