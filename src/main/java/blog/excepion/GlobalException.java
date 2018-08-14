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
//ֻҪʵ������ӿڵ��쳣 ����ȫ���쳣������
public class GlobalException implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//���������Ϣ
		String message="";
		//�ж��Ƿ����Զ�����쳣
		if (ex instanceof MyException) {
			//ǿת��ȡ��������Ϣ
			message=((MyException)ex).getMessage();
		}else {
			message="δ֪����";
		}
		//�ж���������
		HandlerMethod handlerMethod=(HandlerMethod) handler;
		ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if (responseBody!=null) {
			//˵����json����  ����json����
			Map<String, Object> map=new HashMap<>();
			map.put("code", "999999");
			map.put("message", message);
			//map ����ת��json��ʽ
			String json=new Gson().toJson(map);
			//������Ӧͷ���ַ���
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			
			try {
				//json����д���������
				response.getWriter().write(json);
				//ȫ��ˢ��
				response.getWriter().flush();
			} catch (IOException el) {
				// TODO: handle exception
				el.printStackTrace();
			}
			//����һ���յ�modelandview ��ʾ�Ѿ��ֶ�������Ӧ
			return new ModelAndView();
		}
		
		
		//action����
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("error");
		
		
		return modelAndView;

	}

	

}



















