package exception;

public class SystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3452750901052033710L;
	String msg;
	public SystemException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return this.msg;
	}
	
}
