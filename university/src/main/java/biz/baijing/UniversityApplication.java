package biz.baijing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


//@ServletComponentScan      // Spring项目使用 Java 类的注解
@SpringBootApplication
public class UniversityApplication {

    public static void main(String[] args) {

        SpringApplication.run(UniversityApplication.class, args);

    }

    // 声明第三方 Bean
//    @Bean                                    //
//    public ThreeBeanGo threeBeanGo(){
//        return  new ThreeBeanGo;
//    }

}

