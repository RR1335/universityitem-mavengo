package biz.baijing.controller;


import biz.baijing.Emp;
import biz.baijing.JwtsToken;
import biz.baijing.Result;
import biz.baijing.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录：{}",emp);
        Emp e = empService.login(emp);

        if (e == null) {
            return Result.error("用户名或密码错误。");
        }

        Map<String , Object> claims = new HashMap<>();
        claims.put("id",e.getId());
        claims.put("username",e.getUsername());
        claims.put("name",e.getName());

        String jwt = JwtsToken.createJwt(claims);


        return Result.success(jwt);
        // return e != null?Result.success(e):Result.error("用户名密码错误.")
    }




}
