package blog.excepion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
//只要实现这个接口的异常 就是全局异常处理器
public class GlobalException implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//定义错误信息
		String message="";
		//判断是否是自定义的异常
		if (ex instanceof MyException) {
			//强转并取出错误信息
			message=((MyException)ex).getMessage();
		}else {
			message="未知错误";
		}
		//判断请求类型
		HandlerMethod handlerMethod=(HandlerMethod) handler;
		ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if (responseBody!=null) {
			//说明是json请求  返回json数据
			Map<String, Object> map=new HashMap<>();
			map.put("code", "999999");
			map.put("message", message);
			//map 数据转成json格式
			String json=new Gson().toJson(map);
			//设置响应头及字符集
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			
			try {
				//json数据写出到浏览器
				response.getWriter().write(json);
				//全部刷出
				response.getWriter().flush();
			} catch (IOException el) {
				// TODO: handle exception
				el.printStackTrace();
			}
			//返回一个空的modelandview 表示已经手动生成响应
			return new ModelAndView();
		}
		
		
		//action请求
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("error");
		
		
		return modelAndView;

	}

	

}



















