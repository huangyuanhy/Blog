package blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.entity.Result;
import blog.entity.UserInfo;
import blog.excepion.MyException;
import blog.service.UserInfoService;

@Controller
@RequestMapping("admin")
public class UserInfoAction {
	// �����������仰  ��־��Ϣ
	private Logger log = Logger.getLogger(this.getClass());

	
	@Autowired
	private UserInfoService userInfoService;
	

/**
 * ��ҳҳ��
 * @return
 */
	@RequestMapping("index.action")
	public String index() {
		return "admin/index";
	}
	/**
	 * ��ת����½ҳ��
	 * @return
	 */
	
	@RequestMapping("login.action")
	public String login() {
		return "admin/login";
	}
	
	/**
	 * ��ת����½ҳ��  ����json ������json����  Ҳ�����ò���httpRequset ����ȡ������Ȼ����д���
	 * @return
	 * @throws MyException 
	 */
	@RequestMapping("login.json")
	public @ResponseBody Result loginJson(ModelMap map,
			 /*@RequestBody String name,
			 @RequestBody String password,*/
			 HttpServletRequest request) throws MyException {
		
		String name = (String) request.getParameter("name");
		String password = (String) request.getParameter("password");
		// 1 �����¼��������δ��д��ֱ�ӷ��ص�¼ҳ��
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			//return Result.error("�û��������벻��Ϊ��");
			//ʹ���Զ����쳣������
			throw new MyException("�û����������벻Ϊ��");
		}
		
		// 2 У���û����������Ƿ���ȷ
		UserInfo userInfo = userInfoService.selectUser(name, password);
		if (userInfo==null) {
			//return Result.error("�û��������벻��ȷ");
			//ʹ���Զ����쳣������
			throw new MyException("�û����������벻��ȷ");
		}
		//3 ����session
		request.getSession().setAttribute("userInfo", userInfo);
		
		// 4 ��¼�ɹ���������ҳ
		return Result.success().add("key", "���������Ե�");
	}
	
	
	/**
	 * �˳���½ ����session
	 * @return
	 */
	@RequestMapping("/out.action")
	public String out(HttpSession session) {
		
		if (session!=null) {
			session.invalidate();
		}
		return "admin/login";
		
	}
	
	
	//�����ֻ�ǳ���������Ŀ �������ã��Ժ󶼲��ù�
	/**
	 * �û���¼
	 * @param loginName ��¼��
	 * @param passWord ��¼����
	 */
	/*@RequestMapping("login.action")
	public String login(ModelMap map,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "password") String password) {
		
		// �����¼��������δ��д��ֱ�ӷ��ص�¼ҳ��
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			return "login";
		}
		
		// У���û����������Ƿ���ȷ
		UserInfo userInfo = userInfoService.selectUser(name, password);
		if (userInfo==null) {
			return "login";
		}
		
		// ��¼�ɹ���������ҳ
		return "home";
	}*/
}
