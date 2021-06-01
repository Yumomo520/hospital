package com.goodmap.hospital.config.shiro;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 李美泉
 * @Data 2020/9/18 time
 * @Description
 **/
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 允许哪些Origin发起跨域请求,nginx下正常
        // response.setHeader( "Access-Control-Allow-Origin", config.getInitParameter( "AccessControlAllowOrigin" ) );
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许请求的方法
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
        // 多少秒内，不需要再发送预检验请求，可以缓存该结果
        response.setHeader("Access-Control-Max-Age", "3600");
        // 表明它允许跨域请求包含xxx头
        response.setHeader("Access-Control-Allow-Headers", "x-auth-token,Origin,authToken,X-Requested-With,Content-Type, Accept");
        //是否允许浏览器携带用户身份信息（cookie）
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //解决跨域前台获取不到此属性的问题
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        // response.setHeader( "Access-Control-Expose-Headers", "*" );
        //浏览器进行跨域请求前，会先预请求，如果在options请求之后获取到的响应是拒绝性质的,例如500等http状态,那么它就会停止第二次的真正请求的访问
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(200);
            return;
        }
        // Filter 只是链式处理，请求依然转发到目的地址
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
