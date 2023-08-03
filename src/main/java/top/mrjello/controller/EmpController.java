package top.mrjello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.mrjello.anno.Log;
import top.mrjello.pojo.Emp;
import top.mrjello.pojo.PageBean;
import top.mrjello.pojo.Result;
import top.mrjello.service.EmpService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author jason@mrjello.top
 * @date 2023/6/29 20:19
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询员工
     * @param page 页码
     * @param pageSize 每页大小
     * @return 员工列表
     */
    @GetMapping
    public Result pageEmps(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           String name, Short gender,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询员工列表，页码：{}，每页大小：{}, 姓名:{}, 性别: {}, 开始时间: {}, 结束时间: {}",
                page, pageSize,name, gender, begin, end);
        //调用service层方法分页查询
        PageBean pageBean =empService.pageEmps(page, pageSize,name, gender, begin, end);
        return Result.success(pageBean);
    }

    /**
     * 删除员工
     * @param ids 员工id列表
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteEmpByIds(@PathVariable List<Integer> ids) {
        log.info("删除员工，ids: {}", ids);
        //调用service层方法删除员工
        empService.deleteEmpByIds(ids);
        return Result.success(

        );
    }


    /**
     * 添加员工
     * @param emp 员工
     */
    @Log
    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        log.info("添加员工，emp: {}", emp);
        //调用service层方法添加员工
        empService.addEmp(emp);
        return Result.success();
    }

    /**
     * 查询员工
     * @param id 员工
     */
    @GetMapping("/{id}")
    public Result queryEmpById(@PathVariable Integer id) {
        log.info("查询员工，id: {}", id);
        //调用service层方法查询员工
        Emp emp = empService.queryEmpById(id);
        return Result.success(emp);
    }

    /**
     * 更新员工
     * @param emp 员工
     */
    @Log
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        log.info("更新员工，emp: {}", emp);
        //调用service层方法更新员工
        empService.updateEmp(emp);
        return Result.success();
    }

}
