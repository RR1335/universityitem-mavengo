package biz.baijing.tryaop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Order(1)         // AOP 执行次序 ， before 执行数字小优先，after 数字大优先
@Slf4j
//@Component
//@Aspect
public class TryAspect {

    // 切入点表达式，引用
    @Pointcut("execution(* biz.baijing.service.impl.DeptServiceImpl.*(..))")
    public void poct() {}

    @Before("poct()")      // * 任意方法 ， .. 形参任意
    public void before(){
        log.info("Before ...");
    }

    @After("poct()")
    public void after(){
        log.info("After ...");
    }

    @Around("poct()")
    public Object around(ProceedingJoinPoint proceedingjoinPoint) throws Throwable{
        log.info("Around before ...");

        // 调用目标方法
        Object proceed = proceedingjoinPoint.proceed();

        log.info("around after");
        return proceed;
    }

    @AfterReturning("poct()")
    public void afterReturning(){
        log.info("AfterReturning ...");
    }

    @AfterThrowing("poct()")
    public void afterThrowing(){
        log.info("After Throwing ...");
    }

}
