package top.mrjello.mapper;

import org.apache.ibatis.annotations.*;
import top.mrjello.pojo.Dept;

import java.util.List;

/**
 * @author jason@mrjello.top
 * @date 2023/6/29 20:19
 */
@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门信息
     * @return List<Dept>
     */
    @Select("select * from dept")
    List<Dept> getDeptList();

    /**
     * 根据id删除部门信息
     * @param id 部门id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteDeptById(Integer id);

    /**
     * 添加部门信息
     * @param dept 部门信息
     */
    @Insert("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void addDept(Dept dept);

    /**
     * 根据id查询部门信息
     *
     * @param id 部门id
     * @return List<Dept>
     */
    @Select("select * from dept where id = #{id}")
    Dept queryDeptById(Integer id);

    /**
     * 根据id更新部门信息
     * @param dept 部门信息
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void updateDeptById(Dept dept);
}
