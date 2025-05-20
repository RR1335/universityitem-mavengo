package biz.baijing.tryaop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Slf4j
//@Component
//@Aspect
public class TryJoinPoint {
//
//    @Pointcut("@annotation(biz.baijing.aop.TryLogging)")
//    public void poct() {}

    @Pointcut("execution(* biz.baijing.service.impl.DeptServiceImpl.*(..))")
    public void poct() {}

    @Before("poct()")
    public void before(JoinPoint joinPoint) {
        log.info("before _ TryJoinPoint");
    }


    @Around("poct()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around _ TryJoinPoint");
        // 获取目标对象的类名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("around 类名 ClassName = " + className);
        // 获取目标对象的方法名
        String methodeDecName = joinPoint.getSignature().getDeclaringType().getName();
        log.info("around 方法dec MethodName = " + methodeDecName);
        String methodeName = joinPoint.getSignature().getName();
        log.info("around 方法 MethodName = " + methodeName);
        // 获取目标对象传递的参数 — 运行时 Runtime
        Object[] args = joinPoint.getArgs();
        log.info("around 参数 args = " + Arrays.toString(args));
        // 放行 目标方法执行
        Object proceed = joinPoint.proceed();
        log.info("around 返回值 proceed = " + proceed);

        log.info("Around After …… TryJoinPoint");
        return proceed;
    }

}
