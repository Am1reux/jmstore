package cn.tedu.jmstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.jmstore.entity.User;
import cn.tedu.jmstore.service.ex.InsertException;
import cn.tedu.jmstore.service.ex.UsernameDuplicateException;
import cn.tedu.jmstore.service.impl.UserServiceImpl;
import cn.tedu.jmstore.util.JsonResult;
//@RestController = @Controller+@ResponseBody(如果不给浏览器响应页面，则需要加这个注解)

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
	
	@Autowired
	private UserServiceImpl service;
	
	//自定义方法，处理用户在页面提交的注册请求
	@RequestMapping("reg")
	public JsonResult<Void> handleReg(User user) {
		//1.获取用户提交的注册数据:(1)使用同名参数 (2)使用自定义类型User
		//2.调用业务层处理业务逻辑及数据
		//	(1)注册成功，给用户响应状态码20
		//	(2)用户名被占用，给用户响应状态码30，及异常信息 
		//	(3)添加数据异常，给用户响应状态码40，及异常信息 
		//3.分析业务层返回的处理结果，决定怎么给用户进行响应
		JsonResult<Void> jr = new JsonResult();
		service.reg(user);
		jr.setState(20);
		return jr;
	}
	
	//自定义方法，处理用户在页面提交的登陆请求
	@RequestMapping("login")
	public JsonResult<User> login(
		String username,String password, HttpSession session){

		// 调用service的login()进行登录
		User user=service.login(username,password);
		
		// 向session中添加uid
		session.setAttribute("uid",user.getUid());

		// 向session中添加username
		session.setAttribute("username",user.getUsername());

		JsonResult<User> jr=new JsonResult();
		jr.setData(user);
		jr.setState(20);   //或者写SUCCESS，BaseController中定义的常量

		return jr;
	}
	
	@RequestMapping("change_password")
	public JsonResult<Void> changePassword(@RequestParam("old_password") String oldPassword, @RequestParam("new_password") String newPassword,HttpSession session){
		
		// 从session中获取uid
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		
		// 从session中获取username
		String username=session.getAttribute("username").toString();

		service.changePassword(uid,oldPassword,newPassword,username);
		return new JsonResult<Void>(SUCCESS);
	}
	
	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(User user,HttpSession session){
		// 从session中获取uid
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		
		// 从session中获取username
		String username=session.getAttribute("username").toString();

		// 调用service方法更新用户数据
		service.changeInfo(uid, username, user);
		
		return new JsonResult<>(SUCCESS);
	}

	@RequestMapping("get_by_uid")
	public JsonResult<User> getByUid(HttpSession session){
		// 从session中获取uid
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());

		// 调用service获取用户数据
		User user=service.getByUid(uid);

		// 返回用户数据   需要给JsonResult添加带这两个参数的构造方法
		return new JsonResult<User>(SUCCESS, user);
	}
}
