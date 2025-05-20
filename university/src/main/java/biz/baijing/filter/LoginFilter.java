package biz.baijing.filter;

import biz.baijing.JwtsToken;
import biz.baijing.Result;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 请求 URL
        String url = request.getRequestURL().toString();
        log.info("请求的 url: {}", url);

        // 判断 URL 是否包含 login
        if (url.contains("login")) {
            log.info("登录操作，执行 doFilter");
            filterChain.doFilter(request, response);

            return;
        }

        // 获取令牌 token
        String jwt = request.getHeader("token");

        // 判断 token 是否有效
        if(!StringUtils.hasLength(jwt)){
            log.info("请求 token 为空，返回未登录状态。");
            Result error = Result.error("未登录，请重新登录");
            // 将对象 转换为 JSON —— Alibaba fastjson
            String notLoginInfo = JSON.toJSONString(error);
            response.getWriter().write(notLoginInfo);        // 返回错误信息给 浏览器（客户端）

            return;
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

            return;
        }

        // token 有效，执行登录
        log.info("令牌 ok；doFilter");
        filterChain.doFilter(request, response);
    }
}
