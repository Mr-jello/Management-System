package top.mrjello.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jason@mrjello.top
 * @date 2023/6/29 20:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code; // 200:成功 400:失败
    private String msg; // 提示信息
    private Object data; // 返回数据

    //增删改响应成功
    public static Result success() {
        return new Result(1, "success", null);
    }

    //查询响应成功
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    //响应失败
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }

}
