package pl.silverpoint.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuditingInterceptor extends HandlerInterceptorAdapter {

	Logger logger = Logger.getLogger("auditLogger");
	private String modifier;
	private String user;
	private String userId;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		if(request.getRequestURI().endsWith("users/add") && request.getMethod().equals("POST")
			|| request.getRequestURI().endsWith("users/edit") && request.getMethod().equals("POST")){
			modifier = request.getRemoteUser();
			user = request.getParameterValues("firstName")[0] + " " + request.getParameterValues("lastName")[0];
		}else if(request.getRequestURI().endsWith("users/delete") && request.getMethod().equals("GET")){
			modifier = request.getRemoteUser();
			userId = request.getParameterValues("id")[0];
		}
		return true;
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception{
		if(request.getRequestURI().endsWith("users/add") && response.getStatus()==302){
			logger.info(String.format("New user [%s] added by %s at %s", user, modifier, getCurrentTime()));
		}else if(request.getRequestURI().endsWith("users/edit") && response.getStatus()==302){
			logger.info(String.format("User [%s] edited by %s at %s", user, modifier, getCurrentTime()));
		}else if(request.getRequestURI().endsWith("users/delete") && response.getStatus()==302){
			logger.info(String.format("User with id [%s] was deleted by %s at %s", userId, modifier, getCurrentTime()));
		}
	}
	
	private String getCurrentTime(){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy 'at' hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		
		return formatter.format(calendar.getTime());
	}
}
