package biz.baijing.service.impl;

import biz.baijing.Emp;
import biz.baijing.PageBean;
import biz.baijing.mapper.EmpMapper;
import biz.baijing.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
员工接口的实现类
 */

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    /**
     * 员工信息查询，含查询条件
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    /**
     * 删除员工信息，支持批量删除
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /**
     * 新增员工
     * @param emp
     */
    @Override
    public void sava(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 更新员工信息
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }

//    /**
//     * 分页查询
//     * @param page
//     * @param pageSize
//     * @return
//     */
/*    @Override
    public PageBean page(Integer page, Integer pageSize) {
        // 获取总记录数
        Long count = empMapper.count();
        // 分页查询的列表，结果
        Integer start = (page - 1) * pageSize;
        List<Emp> list = empMapper.page(start, pageSize);
        // 封装结果到 PageBean
        PageBean pageBean = new PageBean(count, list);
        return pageBean;
    }*/

//    /**
//     * 通过 pagehelper 重写 page 接口
//     * @param page
//     * @param PageSize
//     * @return
//     */
//    @Override
//    public PageBean page(Integer page, Integer PageSize){
//        // 获取分页参数
//        PageHelper.startPage(page,PageSize);
//
//        List<Emp> empList = empMapper.list();
//        // 强转 pagehelper 的 Page类
//        Page<Emp> pb = (Page<Emp>) empList;
//
//        PageBean pageBean = new PageBean(pb.getTotal(),pb.getResult());
//
//        return pageBean;
//
//    }



}
