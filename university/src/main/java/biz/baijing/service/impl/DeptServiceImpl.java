package biz.baijing.service.impl;

import biz.baijing.Dept;
import biz.baijing.DeptLog;
import biz.baijing.mapper.DeptMapper;
import biz.baijing.mapper.EmpMapper;
import biz.baijing.service.DeptService;
import biz.baijing.service.DeptLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/*
部门接口的实现类
 */
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    // Service 层调用 DAO层 需要 Mapper 的 IOC 注入
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;


    /**
     * 查询部门列表
     * @return
     */
//    @TryLogging
    @Override
    public List<Dept> list() {

        return deptMapper.list();
    }

    /**
     * 删除部门
     * @param id
     */
    // Spring 事物管理 , 默认只有运行时异常才会回滚
    // rollbackFor 配置回滚条件
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        // 判断部门下是否有员工
//        Integer c = empMapper.getByDeptId(id);
//        if (c > 0) {
//            System.out.println("部门下有员工" + c + "，确定是否删除");
//        }

        try {
            // 判断 id 是否存在，存在继续；不存在，输出不存在，报错结束
            deptMapper.deleteById(id);

            // 根据部门ID删除该部门的员工信息
            empMapper.deleteByDeptId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {                                    // 事物传播行为
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("解散部门，删除的部分 " + id );
            deptLogService.insert(deptLog);
        }
    }


    /**
     * 新增部门
     */
//    @TryLogging
    @Override
    public void add(Dept dept) {
        // 前端传递的部门信息 — 部门名称 ；需要补全信息
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        // 判断 新增部门的名称是否存在，存在则结束 —— 返回一个错误信息（部门已存在请重新输入）
//        Integer c = deptMapper.getByName(dept);
//        if( c > 0 ){
//            log.info("查询结果 {}",c);
//            System.out.println("已存在，请重新输入：");
//        }

        deptMapper.insert(dept);

    }


    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }


}
