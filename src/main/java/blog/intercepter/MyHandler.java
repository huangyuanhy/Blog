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
 *���������е�ַ���ش���
 */
public class MyHandler implements HandlerInterceptor {


	/* ��URL���ؽ����ж�
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//��ȡ�����ַ
		String requestURI = request.getRequestURI();
		
		//�������ַ���з���
		if (requestURI.indexOf("login")>=0 || requestURI.indexOf("portal")>=0) {
			return true;
		}
		//�ж�session�Ƿ���ڣ����Ƿ��Ѿ���½
		HttpSession session = request.getSession(false);
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		if (userInfo!=null) {
			//�Ѿ���½  ����
			return true;
		}
		
		//ִ�е������ʾ�û������Ҫ��֤���Ƿ���Ȩ�� ��ת����½ҳ��
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
