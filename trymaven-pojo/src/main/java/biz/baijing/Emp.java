package biz.baijing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 员工实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private String image;
    private Short job;                // 岗位 — 1. 班主任 2. 讲师 3. 学工主管 4. 教研主管 5. 咨询师
    private Integer deptId;           // 部门 — 1. 学工部 2. 教研部 3. 咨询部 4. 就业部 5. 人事部
    private LocalDate entrydate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
