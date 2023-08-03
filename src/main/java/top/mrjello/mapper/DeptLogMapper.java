package top.mrjello.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.mrjello.pojo.DeptLog;

/**
 * @author jason@mrjello.top
 * @date 2023/7/3 21:08
 */
@Mapper
public interface DeptLogMapper {

    @Insert("insert into dept_log(create_time, description) values (#{createTime},#{description})")
    void insertDeptLog(DeptLog deptLog);
}
