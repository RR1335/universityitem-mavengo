package biz.baijing.controller;

import biz.baijing.Dept;
import biz.baijing.Result;
import biz.baijing.annotation.OpLog;
import biz.baijing.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
部门管理 Controller
 */

//@Lazy          // 延迟初始化，Bean 使用的时候生成 Bean
@Slf4j
//@Scope( value =  "prototype")       // 每一次使用创建新的实例对象
@RestController
@RequestMapping("/depts")
public class DeptController {

    // slf4j Logger
    // private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

    // 限定请求方式 GET
    // @RequestMapping(value = "/depts",method = RequestMethod.GET)

    // Controller 层调用 service 层，需要 service 层的 IOC 注入
    @Autowired
    private DeptService deptService;

//    public  DeptController() {
//        System.out.println("DeptController ……");
//    }
//

    /**
     * 查询部门列表
     * @return
     */
//    @GetMapping("/depts")
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据。");

        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }


    /**
     * 删除部门，根据 id
     * @param id
     * @return
     */

//    @DeleteMapping("/depts/{id}")
    @OpLog
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门：{}", id);
        // 调用 service 删除部门
        deptService.delete(id);

        return Result.success();
    }

    /**
     * 增加部门
     * @param dept
     * @return
     */
    // 增加部门，前端传递的是 JSON 格式数据，通过 @RequestBody 接收 JSON 数据
//    @PostMapping("/depts")
    @OpLog
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @OpLog
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门名称:{}", dept);
        deptService.update(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("部门信息：{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

}
