package cn.tedu.jmstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.jmstore.service.ex.ServiceException;
import cn.tedu.jmstore.util.JsonResult;
import cn.tedu.jmstore.service.ex.*;

public abstract class BaseController {

protected static final Integer SUCCESS=20;
// 静态常量的命名：所有字母都大写，单词用_隔开，尽量说明白，不要嫌长
protected static final Integer ERROR_USERNAME_DUPLICATE=30;
/**
 * 对控制器中的异常进行统一处理
 * 
 */
@ExceptionHandler(ServiceException.class)
@ResponseBody
	public JsonResult<Void> handlerException(Throwable e){
		// 根据不同异常的类型提供不同的处理方式
		// 现在的处理方式是根据不同的类型，返回不同的状态码
		JsonResult<Void> jr = new JsonResult<>(Integer.valueOf(e.getMessage()));
		
		if(e instanceof UsernameDuplicateException) {
			jr.setState(ERROR_USERNAME_DUPLICATE);
		}else if(e instanceof UserNotFoundException) {
			jr.setState(31);
		}else if(e instanceof PasswordNotMatchException) {
			jr.setState(32);
		}else if(e instanceof ProductNotFoundException) {
	        jr.setState(36);
		}else if(e instanceof InsertException) {
			jr.setState(40);
		}else if(e instanceof UpdateException) {
			jr.setState(41);
		}
		
		return jr;
	}

	public String getUsernameFromSession(HttpSession session) {
		// 从session中获取username
		return session.getAttribute("username").toString();
	}
	
	public Integer getUidFromSession(HttpSession session) {
		// 从session中获取uid
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
}