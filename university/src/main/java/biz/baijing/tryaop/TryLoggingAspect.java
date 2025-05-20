package biz.baijing.tryaop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TryLoggingAspect {

    // 切入点表达式，引用
    @Pointcut("@annotation(biz.baijing.tryaop.TryLogging)")
    public void poct() {}

    @Before("poct()")      // * 任意方法 ， .. 形参任意
    public void before(){
        log.info("Before ...");
    }

    @After("poct()")
    public void after(){
        log.info("After ...");
    }
}
