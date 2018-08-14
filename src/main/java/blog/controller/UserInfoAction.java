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
	// 在类下添加这句话  日志信息
	private Logger log = Logger.getLogger(this.getClass());

	
	@Autowired
	private UserInfoService userInfoService;
	

/**
 * 首页页面
 * @return
 */
	@RequestMapping("index.action")
	public String index() {
		return "admin/index";
	}
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	
	@RequestMapping("login.action")
	public String login() {
		return "admin/login";
	}
	
	/**
	 * 跳转到登陆页面  接收json 并返回json数据  也可以用参数httpRequset 来获取参数，然后进行处理
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
		// 1 如果登录名或密码未填写，直接返回登录页面
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			//return Result.error("用户名或密码不能为空");
			//使用自定义异常来处理
			throw new MyException("用户名或者密码不为空");
		}
		
		// 2 校验用户名、密码是否正确
		UserInfo userInfo = userInfoService.selectUser(name, password);
		if (userInfo==null) {
			//return Result.error("用户名或密码不正确");
			//使用自定义异常来处理
			throw new MyException("用户名或者密码不正确");
		}
		//3 保存session
		request.getSession().setAttribute("userInfo", userInfo);
		
		// 4 登录成功，进入主页
		return Result.success().add("key", "我是来测试的");
	}
	
	
	/**
	 * 退出登陆 销毁session
	 * @return
	 */
	@RequestMapping("/out.action")
	public String out(HttpSession session) {
		
		if (session!=null) {
			session.invalidate();
		}
		return "admin/login";
		
	}
	
	
	//下面的只是初步整合项目 做测试用，以后都不用管
	/**
	 * 用户登录
	 * @param loginName 登录名
	 * @param passWord 登录密码
	 */
	/*@RequestMapping("login.action")
	public String login(ModelMap map,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "password") String password) {
		
		// 如果登录名或密码未填写，直接返回登录页面
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			return "login";
		}
		
		// 校验用户名、密码是否正确
		UserInfo userInfo = userInfoService.selectUser(name, password);
		if (userInfo==null) {
			return "login";
		}
		
		// 登录成功，进入主页
		return "home";
	}*/
}
