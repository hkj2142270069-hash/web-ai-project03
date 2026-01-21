package org.example.t.mapper;

import org.apache.ibatis.annotations.*;
import org.example.t.pojo.Emp;
import org.example.t.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    //-----------------------原始分页查询
    /**
     * 查询员工数量
     * @return
     */
   // @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
    //public Long count();

  //  @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc limit #{start},#{pageSize}")
   // public List<Emp> list(Integer start, Integer pageSize);
   // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id=d.id order by  e.update_time desc ")
   // public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    List<Emp> list(EmpQueryParam empQueryParam);
    // EmpMapper.java


    /*
    * 新增员工
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")//获取生成的主键--主键返回
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)" +
            " values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate}, #{deptId},#{createTime},#{updateTime})")

            void insert(Emp emp);



    void deleteByIds(List<Integer> ids);
//根据id查询员工
    Emp getById(Integer id);


    void updateById(Emp emp);
    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();
    /**
     * 统计员工性别信息
     */
    @MapKey("name")
    List<Map> countEmpGenderData();

    List<Emp> selectAll();

@Select("select id ,username,name from emp where username=#{username} and password=#{password}")
    Emp selectUsernameAndPassword(Emp emp);
}

