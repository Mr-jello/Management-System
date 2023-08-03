package top.mrjello.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mrjello.mapper.EmpMapper;
import top.mrjello.pojo.Emp;
import top.mrjello.pojo.PageBean;
import top.mrjello.service.EmpService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jason@mrjello.top
 * @date 2023/6/29 20:26
 */
@Service
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmpMapper empMapper;

    //1.1原始分页查询方法实现类
/*
    @Override
    public PageBean pageEmps(Integer page, Integer pageSize) {
        //1.获取员工总数
        Long countEmps = empMapper.countEmps();

        //2.分页查询员工
        //2.1计算初始索引: (page-1)*pageSize
        List<Emp> empList = empMapper.pageEmps((page - 1) * pageSize, pageSize);

        //3.封装PageBean对象
        return new PageBean(countEmps, empList);
    }
*/

    //1.2使用PageHelper分页查询方法实现类
    /**
     * 分页查询员工
     * @param page 页码
     * @param pageSize 每页大小
     * @return 员工列表
     */
    @Override
    public PageBean pageEmps(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);
        //2.执行查询
        List<Emp> empList = empMapper.queryEmpsByPage(name, gender, begin, end);
        Page<Emp> empPage = (Page<Emp>) empList;

        //3.封装PageBean对象
        return new PageBean(empPage.getTotal(), empPage.getResult());
    }

    /**
     * 删除员工
     * @param ids 员工id列表
     */
    @Override
    public void deleteEmpByIds(List<Integer> ids) {
        empMapper.deleteEmpByIds(ids);
    }

    /**
     * 添加员工
     * @param emp 员工
     */
    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.addEmp(emp);
    }

    /**
     * 查询员工
     * @param id 员工
     */
    @Override
    public Emp queryEmpById(Integer id) {
        return empMapper.queryEmpById(id);
    }

    /**
     * 更新员工
     * @param emp 员工
     */
    @Override
    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.queryEmpByUsernameAndPassword(emp);
    }
}
