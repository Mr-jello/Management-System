package top.mrjello.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author jason@mrjello.top
 * @date 2023/7/3 21:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLog {
    private Integer id; //ID
    private LocalDateTime createTime; //创建时间
    private String description; //描述
}
