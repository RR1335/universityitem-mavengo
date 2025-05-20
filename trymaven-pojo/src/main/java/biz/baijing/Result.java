package biz.baijing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;    // 返回代码 1. 成功 ， 0 失败
    private String msg;      // 返回描述信息
    private Object data;     // 返回数据

    /*
      — 增删改 ， 成功的返回 —
     */
    public static Result success() {
        return new Result(1, "SUCCESS !", null);
    }

    /*
      — 查询，成功返回 —
     */
    public static Result success(Object data) {
        return new Result(1, "SUCCESS !", data);
    }

    /*
     — 失败，返回值；重点是 meg 要能直接定位错误 —
     */
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }

}
