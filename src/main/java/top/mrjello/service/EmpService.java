package top.mrjello.service;

import org.springframework.format.annotation.DateTimeFormat;
import top.mrjello.pojo.Emp;
import top.mrjello.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * @author jason@mrjello.top
 * @date 2023/6/29 20:25
 */
public interface EmpService {
    /**
     * 分页查询员工
     * @param page 页码
     * @param pageSize 每页大小
     * @return 员工列表
     */
    PageBean pageEmps(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 删除员工
     * @param ids 员工id列表
     */
    void deleteEmpByIds(List<Integer> ids);

    /**
     * 添加员工
     * @param emp 员工
     */
    void addEmp(Emp emp);

    /**
     * 查询员工
     * @param id 员工
     */
    Emp queryEmpById(Integer id);

    /**
     * 更新员工
     * @param emp 员工
     */
    void updateEmp(Emp emp);

    /**
     * 登录
     * @param emp 员工
     * @return 员工
     */
    Emp login(Emp emp);
}
