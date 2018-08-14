/**
 * 
 */
package blog.intercepter;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import blog.entity.UserInfo;

/**
 * @author Administrator
 *拦截器进行地址拦截处理
 */
public class MyHandler implements HandlerInterceptor {


	/* 对URL拦截进行判断
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//获取请求地址
		String requestURI = request.getRequestURI();
		
		//对特殊地址进行放行
		if (requestURI.indexOf("login")>=0 || requestURI.indexOf("portal")>=0) {
			return true;
		}
		//判断session是否存在，即是否已经登陆
		HttpSession session = request.getSession(false);
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		if (userInfo!=null) {
			//已经登陆  放行
			return true;
		}
		
		//执行到这里表示用户身份需要验证，是否有权限 跳转到登陆页面
		String string="/WEB-INF/page/admin/login.jsp";
		request.getRequestDispatcher(string).forward(request, response);
		return false;
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
}
