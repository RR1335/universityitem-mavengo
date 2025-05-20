package biz.baijing.service;

/*
部门管理
 */


import biz.baijing.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 查询部门
     * @return
     */
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id);



    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);

    void update(Dept dept);

    Dept getById(Integer id);
}
