package biz.baijing;


import biz.baijing.controller.DeptController;
import biz.baijing.pojo.Dept;
import biz.baijing.pojo.Result;
import com.google.gson.Gson;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UniversityApplicationTests {

    @Autowired
    private Gson gson;

    @Test
    public void tesJson(){
        String json = gson.toJson(Result.success());

        System.out.println(json);
    }

}