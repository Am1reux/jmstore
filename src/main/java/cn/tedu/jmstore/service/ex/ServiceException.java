package cn.tedu.jmstore.service.ex;
//该类作为所有业务层异常的父类
/*
 * 如何自定义异常：
 * 	1.继承java提供的异常
 * 	2.添加5个构造方法（右击->source->Generate Constructors from Superclass）
 */
public class ServiceException extends RuntimeException {

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
