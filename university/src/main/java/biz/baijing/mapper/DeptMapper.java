package biz.baijing.mapper;

import biz.baijing.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
部门管理 DAO
 */

@Mapper
public interface DeptMapper {

    /**
     * 查询部门列表
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);



    /**
     * 插入部门
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);


    void update(Dept dept);

    @Select("select * from dept where id=#{id}")
    Dept getById(Integer id);

    @Select("select count(*) from dept where name = #{name}")
    Integer getByName(Dept dept);
}
