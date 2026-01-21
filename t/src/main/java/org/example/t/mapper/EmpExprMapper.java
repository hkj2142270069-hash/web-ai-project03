package org.example.t.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.t.pojo.EmpExpr;

import java.util.List;

@Mapper
/*
   批量保存员工经历信息
 */
public interface EmpExprMapper {
    void insertBatch(List<EmpExpr> exprList);



    /**
     * 根据员工的ID批量删除工作经历信息
     */
    void deleteByEmpIds(List<Integer> empIds);
}
