package tutorial.servletfilter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"*.jpg", "*.png", "*.gif"}, initParams = {
		@WebInitParam(name="notFoundImage", value="/images/default.jpg")
})
public class ImageFilter implements Filter {
	
	private String notFoundImage;
	
	public ImageFilter() {}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		notFoundImage = fConfig.getInitParameter("notFoundImage");
	}
	
	@Override 
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		String servletPath = request.getServletPath();
		
		System.out.println("servlet path: " + servletPath);
		
		String rootPath = request.getServletContext().getRealPath("");
		System.out.println("root path: " + rootPath);
		
		String imagePath = rootPath + servletPath;
		
		System.out.println(imagePath);
		
		File file = new File(imagePath);
		if(file.exists()) {
			chain.doFilter(req, res);
		}else if(!servletPath.equals(this.notFoundImage)) {
			HttpServletResponse response = (HttpServletResponse) res;
			
			response.sendRedirect(request.getContextPath() + this.notFoundImage);
		}
		
	}
}
