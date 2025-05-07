import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("请求进入 Filter: " + req.getRequestURI());
        
        chain.doFilter(request, response); // 放行请求
        
        System.out.println("请求离开 Filter");
    }
}