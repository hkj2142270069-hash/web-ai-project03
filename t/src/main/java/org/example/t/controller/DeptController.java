package org.example.t.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.t.anno.Log;
import org.example.t.pojo.Dept;
import org.example.t.pojo.Result;
import org.example.t.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DeptController {


    @Autowired
    private DeptService deptService;
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
   @GetMapping("/depts")
    public Result list(){
//        System.out.println("查询全部部门信息");
       log.info("查询全部部门信息");
       List<Dept> deptList= deptService.findAll();
       return Result.success(deptList);



    }
    //注意事项：一点声明了@RequestParam注解，那么参数名必须传递，否则报错（默认required为true）
    //@DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id" , required = false) Integer deptId){
//        System.out.println("根据Id删除部门："+deptId);
//        return Result.success();
//    }
    //要求参数一质
    @DeleteMapping("/depts")
    @Log
    public Result delete( Integer id){
       // System.out.println("根据Id删除部门："+id);
        log.info("根据Id删除部门：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }
    @PostMapping("/depts")
    @Log
    public Result add(@RequestBody Dept dept){
//        System.out.println("保存部门："+dept);
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门
     *
     *
     */
//    @GetMapping("/depts/{id}")
//    public Result getInto(@PathVariable("id") Integer deptId){
//       System.out.println("查询部门Id："+deptId);
//       return Result.success();

    //}
    @GetMapping("/depts/{id}")
    public Result getInto(@PathVariable Integer id){
//        System.out.println("查询部门Id："+id);
        log.info("查询部门Id：{}",id);
        Dept dept=deptService.getInto(id);
        return Result.success(dept);

    }

    @PutMapping("/depts")
    @Log
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门："+dept);
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
