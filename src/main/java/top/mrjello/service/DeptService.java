package top.mrjello.service;

import top.mrjello.pojo.Dept;

import java.util.List;

/**
 * @author jason@mrjello.top
 * @date 2023/6/29 20:25
 */
public interface DeptService {
    /**
     * 查询全部部门信息
     * @return List<Dept>
     */
    List<Dept> getDeptList();

    /**
     * 根据id删除部门信息
     * @param id 部门id
     */
    void deleteDeptById(Integer id);

    /**
     * 添加部门信息
     * @param dept 部门信息
     */
    void addDept(Dept dept);

    /**
     * 根据id查询部门信息
     *
     * @param id 部门id
     * @return Dept
     */
    Dept queryDeptById(Integer id);

    /**
     * 根据id更新部门信息
     * @param dept 部门信息
     */
    void updateDeptById(Dept dept);
}
