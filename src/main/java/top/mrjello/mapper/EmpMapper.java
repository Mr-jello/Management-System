package top.mrjello.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.mrjello.pojo.Emp;

import java.time.LocalDate;
import java.util.List;

/**
 * @author jason@mrjello.top
 * @date 2023/6/29 20:19
 */
@Mapper
public interface EmpMapper {

    //1.1原始分页查询方法
/*

     * 查询员工总数
     * @return 员工总数
     *//*
    @Select("select count(*) from emp")
    Long countEmps();

     * 分页查询员工
     * @param initIndex 页码
     * @param pageSize 每页大小
     * @return 员工列表
     *
    @Select("select * from emp limit #{initIndex},#{pageSize}")
    List<Emp> pageEmps(Integer initIndex, Integer pageSize);

*/

    //1.2使用PageHelper分页查询方法
    /**
     * 查询员工总数
     * @return 员工总数
     */
    //@Select("select * from emp") 使用动态sql
    List<Emp> queryEmpsByPage(String name, Short gender, LocalDate begin, LocalDate end);


    /**
     * 删除员工
     * @param ids 员工id列表
     */
    void deleteEmpByIds(List<Integer> ids);

    /**
     * 添加员工
     * @param emp 员工
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void addEmp(Emp emp);

    /**
     * 查询员工
     * @param id 员工
     */
    @Select("select * from emp where id = #{id}")
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
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp queryEmpByUsernameAndPassword(Emp emp);

    /**
     * 根据部门id删除员工
     * @param deptId 部门id
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteEmpByDeptId(Integer deptId);
}
