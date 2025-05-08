package cn.xxywithpq.infrastructure.aop;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/*")  // 添加注解以启用 Filter
public class LogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("请求进入 Filter: " + req.getRequestURI());

		chain.doFilter(request, response); // 放行请求

		System.out.println("请求离开 Filter");
	}
}