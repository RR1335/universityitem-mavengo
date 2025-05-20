package biz.baijing.aopnow;

import biz.baijing.JwtsToken;
import biz.baijing.OperateLog;
import biz.baijing.mapper.OperateLogMapper;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private HttpServletRequest request;     // 获得当前请求对象

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(biz.baijing.annotation.OpLog)")
    public Object insertLogAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // 操作人 id = 登录 id —— 解析 jwt
        String jwt = request.getHeader("token");
        Claims claims = JwtsToken.parseJwt(jwt);
        Integer operateUser = (Integer) claims.get("id");

        // 操作时间 = 当前时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 操作 类名 / 参数 / 返回值
        String className = joinPoint.getTarget().getClass().getName();       // 获取类名
        String methodName = joinPoint.getSignature().getName();
        String methodParams = Arrays.toString(joinPoint.getArgs());

        long start = System.currentTimeMillis();

        Object proceedResult = joinPoint.proceed();                       // 返回值
        String returnValue = JSONObject.toJSONString(proceedResult);

        long end = System.currentTimeMillis();
        // 操作耗时
        long costTime = end - start;
        // 操作日志入库
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志 {}",operateLog);

        return proceedResult;
    }
}
