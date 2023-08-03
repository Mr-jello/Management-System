package top.mrjello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mrjello.anno.Log;
import top.mrjello.pojo.Dept;
import top.mrjello.pojo.Result;
import top.mrjello.service.DeptService;

import java.util.List;

/**
 * @author jason@mrjello.top
 * @date 2023/6/29 20:18
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)

    /**
     * 查询全部部门信息
     * @return Result
     */
    @GetMapping
    public Result getDeptList() {
        log.info("查询全部部门信息");

        //调用service层的方法查询数据
        List<Dept> deptList = deptService.getDeptList();

        return Result.success(deptList);
    }

    /**
     * 根据id查询部门信息
     * @param id 部门id
     * @return Result
     */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteDeptById(@PathVariable Integer id) {
        log.info("根据id删除部门信息:{}", id);
        //调用service层的方法删除数据
        deptService.deleteDeptById(id);
        return Result.success();
    }

    /**
     * 添加部门信息
     * @param dept 部门信息
     * @return Result
     */
    @Log
    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        log.info("添加部门信息:{}", dept);
        //调用service层的方法删除数据
        deptService.addDept(dept);
        return Result.success();
    }


    /**
     * 根据id查询部门信息
     * @param id 部门id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result queryDeptById(@PathVariable Integer id){
        log.info("根据id查询部门信息:{}", id);
        //调用service层的方法查询数据
        Dept dept = deptService.queryDeptById(id);
        return Result.success(dept);
    }

    /**
     * 根据id更新部门信息
     * @param dept 部门信息
     * @return Result
     */
    @Log
    @PutMapping
    public Result updateDeptById(@RequestBody Dept dept){
        log.info("根据id更新部门信息:{}", dept);
        //调用service层的方法更新数据
        deptService.updateDeptById(dept);
        return Result.success();
    }
}
