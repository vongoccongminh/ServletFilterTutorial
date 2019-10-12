package tutorial.servletfilter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {
	
	public LogFilter() {
		
	}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LogFilter init!");
	}
	
	@Override
	public void destroy() {
		System.out.println("LogFilter destroy!");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String servletPath = request.getServletPath();
		System.out.println("#INFO" + new Date() + " - ServletPath :" + servletPath + ", URL =" + request.getRequestURL());
		
		chain.doFilter(req, res);
	}

}
