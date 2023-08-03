package top.mrjello.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.mrjello.pojo.OperateLog;

/**
 * @author jason@mrjello.top
 * @date 2023/7/4 17:40
 */
@Mapper
public interface OperateMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_user, operate_time, class_name, method_name, method_param, return_value, cost_time) " +
            "values (#{operateUser}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    void insertLog(OperateLog operateLog);
}
