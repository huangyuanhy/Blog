/**
 * 
 */
package blog.excepion;

/**
 * @author Administrator
 *针对预期的异常抛出自定义的异常
 */
public class MyException extends Exception {
	private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public MyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		this.message=message;
	}

	
}
