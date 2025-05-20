package biz.baijing.controller;

import biz.baijing.Emp;
import biz.baijing.PageBean;
import biz.baijing.Result;
import biz.baijing.annotation.OpLog;
import biz.baijing.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/*
员工管理 Controller
 */

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 查询员工列表，和条件查询
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end) {
        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize,name, gender, begin, end);
        // 调用 service 分页查询
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);

        return Result.success(pageBean);
    }


    /**
     * 删除员工信息
     * @param ids
     * @return
     */
    @OpLog
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除 ids={}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 新增员工
     * @param emp
     * @return
     */
    @OpLog
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工 {}", emp);
        empService.sava(emp);
        return Result.success();
    }

    /**
     * 根据 id 查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据{}查询员工信息。",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     * @param emp
     * @return
     */
    @OpLog
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工：{}",emp);
        empService.update(emp);
        return Result.success();
    }


}
