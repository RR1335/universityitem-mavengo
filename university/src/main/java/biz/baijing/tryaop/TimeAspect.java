package biz.baijing.tryaop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

@Slf4j
//@Component
//@Aspect
public class TimeAspect {

    @Around("biz.baijing.aop.TryAspect.poct()")        // 切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long start = System.currentTimeMillis();

        // 调用原始方法运行
        Object result = joinPoint.proceed();

        // 记录结束时间，并计算耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法耗时： {} ms", end - start);

        return result;
    }

}
