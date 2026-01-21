package org.example.t.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.example.t.pojo.Clazz;
import org.example.t.pojo.ClazzQueryParam;
import org.example.t.pojo.PageResult;

import java.util.List;
@Mapper
public interface ClazzMapper {
    // ClazzMapper.java
    int countStudentsByClazzId(Integer clazzId);
//根据id查询
    Clazz getById(Integer id) ;


//分页查询
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

//查询所有
   List<Clazz> getAllClazzs();

   //添加
@Options(useGeneratedKeys = true,keyProperty = "id")
@Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void add(Clazz clazz);
//修改
    void update(Clazz clazz);
//删除
    void delete(Integer[] ids);
}
