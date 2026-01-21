package org.example.t.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.t.mapper.EmpMapper;
import org.example.t.pojo.Emp;
import org.example.t.pojo.EmpQueryParam;
import org.example.t.pojo.PageResult;
import org.example.t.pojo.Result;
import org.example.t.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

//员工管理
@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;
//    @GetMapping
//    public Result page(@RequestParam ( defaultValue = "1")Integer page,
//                       @RequestParam ( defaultValue = "10")  Integer pageSize ,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                         @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
//        log.info("分页查询员工信息，参数：{},{},{},{},{},{} ",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult=empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询员工信息，参数：{}",empQueryParam);
        PageResult<Emp> pageResult=empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("保存员工信息，参数：{}",emp);
        empService.save(emp);
        return Result.success();


    }
//   @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("批量删除员工信息，参数：{}",ids);
//        return Result.success();
//    }
    /**
     * 批量删除员工
     */
    /**
     * 批量删除员工
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除部门: ids={} ", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工信息");
        Emp emp=empService.getInto(id);
        return Result.success(emp);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }
    @GetMapping("/list")
    public Result listAll(){
        log.info("查询所有员工信息");
        List<Emp> list=empService.listAll();
        return Result.success(list);
    }

}
