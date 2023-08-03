package top.mrjello.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author jason@mrjello.top
 * @date 2023/7/4 17:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    private Integer id; // 主键
    private String operateUser; // 操作用户id
    private LocalDateTime operateTime; // 操作时间
    private String className; // 操作类名
    private String methodName; // 操作方法名
    private String methodParams; // 操作方法参数
    private String returnValue; // 操作方法返回值
    private Long costTime; // 操作耗时


}
