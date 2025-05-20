package biz.baijing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页的数据
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Long total;      // 总记录数，要和前端一致
    private List rows;       // 列表的集合，要和前端一致

}
