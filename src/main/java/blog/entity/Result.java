/**
 * 
 */
package blog.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * @author Administrator
 *�����Ϣ
 */
public class Result {
	//״̬�� �ɹ� 000000 ʧ�� 999999
	private String code;
	//������Ϣ
	private String message;
	//���ص����� ����ʹ�÷���
	/*private T data;*/
	//����ʹ����ʽ
	private Map<String, Object> data=new HashMap<>();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public static  Result success() {
		Result result=new Result();
		result.setCode("000000");
		result.setMessage("����ɹ�");
		return result;
	}
	public static  Result error(String string) {
		Result result=new Result();
		result.setCode("999999");
		if (StringUtils.isEmpty(string)) {
			result.setMessage("����ʧ��");
		}else {
			
			result.setMessage(string);
		}
		return result;
	}
	public Result add(String key,Object value) {
		this.getData().put(key, value);
		return this;
	}
}
