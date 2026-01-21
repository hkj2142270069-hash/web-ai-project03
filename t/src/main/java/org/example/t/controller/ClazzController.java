package org.example.t.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.t.pojo.*;
import org.example.t.service.ClazzService;
import org.example.t.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询员工信息，参数：{}",clazzQueryParam);
        PageResult<Clazz> pageResult=clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }
    @GetMapping("/list")
    public Result list(){
        log.info("查询全部教师");
        List<Clazz> list = clazzService.getAllClazzs();
        return Result.success(list);
    }
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("保存教师信息，参数：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id{}查询班级", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("更新班级信息，参数：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级，id：{}", id);
        clazzService.deleteClazz(id);
        return Result.success();
    }


}
