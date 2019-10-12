package tutorial.servletfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogToFile implements Filter{
	
	private String logFile;
	
	public LogToFile() {}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		logFile = fConfig.getInitParameter("logFileName");
	}
	
	@Override
    public void destroy() {
        System.out.println("LogToFile destroy!");
    }
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		if(this.logFile != null) {
			logToFile(this.logFile);
		}
		
		chain.doFilter(req, res);
		
	}
	
	public void logToFile(String logFile) {
		System.out.println("Write log to file " + logFile);
	}
}
