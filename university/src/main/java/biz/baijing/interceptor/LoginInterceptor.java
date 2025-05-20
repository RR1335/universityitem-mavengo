package biz.baijing.interceptor;

import biz.baijing.JwtsToken;
import biz.baijing.Result;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        log.info("获取 url:{}", url);

        if (url.contains("login")) {
            log.info("登录操作，执行");
            return true;
        }

        String jwt = request.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("请求 token 为空，返回未登录状态。");
            Result error = Result.error("未登录，请重新登录");
            // 将对象 转换为 JSON —— Alibaba fastjson
            String notLoginInfo = JSON.toJSONString(error);
            response.getWriter().write(notLoginInfo);        // 返回错误信息给 浏览器（客户端）

            return false;
        }

        // token 无效，返回未登录
        try {
            JwtsToken.parseJwt(jwt);
        } catch (Exception e) {
            log.info("令牌错误。");
            e.printStackTrace();      // 打印错误信息

            // jwt 错误，要和客户端交互登录失败。
            Result error = Result.error("未登录，请重新登录");
            // 将对象 转换为 JSON —— Alibaba fastjson
            String notLoginInfo = JSON.toJSONString(error);
            response.getWriter().write(notLoginInfo);        // 返回错误信息给 浏览器（客户端）

            return false;
        }

        // token 有效，执行登录
        log.info("令牌 ok；doFilter");
        return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle doing ...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion doing ...");
    }
}
