package top.mrjello.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mrjello.mapper.DeptMapper;
import top.mrjello.mapper.EmpMapper;
import top.mrjello.pojo.Dept;
import top.mrjello.pojo.DeptLog;
import top.mrjello.service.DeptLogService;
import top.mrjello.service.DeptService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jason@mrjello.top
 * @date 2023/6/29 20:26
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    /**
     * 查询全部部门信息
     * @return List<Dept>
     */
    @Override
    public List<Dept> getDeptList() {
        return deptMapper.getDeptList();
    }

    /**
     * 根据id删除部门信息
     * @param id 部门id
     */
    @Transactional(rollbackFor = Exception.class) //开启事务,所有操作都成功才提交,否则回滚
    @Override
    public void deleteDeptById(Integer id) {
        try {
            deptMapper.deleteDeptById(id);

            //int i = 1 / 0; //模拟异常

            empMapper.deleteEmpByDeptId(id);
        }finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了删除部门操作，此次删除的部门Id为：" + id);
            deptLogService.insertDeptLog(deptLog);
        }
    }

    /**
     * 添加部门信息
     * @param dept 部门信息
     */
    @Override
    public void addDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.addDept(dept);
    }

    /**
     * 根据id查询部门信息
     *
     * @param id 部门id
     * @return List<Dept>
     */
    @Override
    public Dept queryDeptById(Integer id) {
        return deptMapper.queryDeptById(id);
    }

    /**
     * 根据id更新部门信息
     * @param dept 部门信息
     */
    @Override
    public void updateDeptById(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDeptById(dept);
    }


}
