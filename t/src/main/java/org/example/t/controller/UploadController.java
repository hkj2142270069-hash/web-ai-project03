package org.example.t.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.t.pojo.Result;
import org.example.t.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    /**
     * 本地磁盘存储方案
     * @param name
     * @param age
     * @param file
     * @return

  @RequestMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收参数 {},{},{}",name,age,file);
       //获取原始文件名
      String originalFilename = file.getOriginalFilename();
      //新的文件名
      String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
      String newFileName = UUID.randomUUID().toString() + extension;
      //保存文件
      file.transferTo(new java.io.File("D:/images/"+newFileName));
     return Result.success();
   }*/
    /**
     * 阿里云OSS存储方案
     *
     *
     */
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("接收参数 {}",file.getOriginalFilename());
        //将文件交给oss存储
        String url=  aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        log.info("文件上传成功，返回url:{}",url);
        return Result.success(url);

    }
}
