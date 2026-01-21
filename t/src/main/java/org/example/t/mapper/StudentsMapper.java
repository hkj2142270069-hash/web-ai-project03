package org.example.t.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.t.pojo.Student;
import org.example.t.pojo.StudentsQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentsMapper {
    List<Student> list(StudentsQueryParam studentsQueryParam);



    void add(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void delete(Integer[] ids);
    @Select("SELECT c.name AS clazz, COUNT(s.id) AS total " +
            "FROM clazz c " +
            "LEFT JOIN student s ON c.id = s.clazz_id " +
            "GROUP BY c.id, c.name " +
            "ORDER BY total DESC")
    List<Map<String, Object>> countStudentByClazz();


    // StudentsMapper.java
    @Select("SELECT degree AS name, COUNT(*) AS value " +
            "FROM student " +
            "GROUP BY degree " +
            "ORDER BY value DESC")
    List<Map<String, Object>> countStudentByDegree();

}
