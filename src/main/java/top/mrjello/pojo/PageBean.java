package top.mrjello.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jason@mrjello.top
 * @date 2023/7/1 16:49
 */

// 用于封装分页查询的封装类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private Long total; // 总记录数
    private List rows; // 当前页的数据集合
}
