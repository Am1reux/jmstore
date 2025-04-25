package cn.tedu.jmstore.service;

import org.springframework.stereotype.Service;

import cn.tedu.jmstore.entity.User;
import cn.tedu.jmstore.service.ex.PasswordNotMatchException;
import cn.tedu.jmstore.service.ex.UpdateException;
import cn.tedu.jmstore.service.ex.UserNotFoundException;

public interface IUserService {
	
	//自定义注册功能业务层的方法
	void reg(User user);
	
	//自定义登陆功能业务层的方法
	User login(String username,String password) throws UserNotFoundException, PasswordNotMatchException;
	
	//自定义修改密码功能业务层的方法
	void changePassword (
			Integer uid,
			String oldPassword,
			String newPassword,
			String modifiedUser)
				throws 	
					UserNotFoundException, PasswordNotMatchException, UpdateException;
	
	//自定义修改用户信息功能业务层的方法
	void changeInfo(Integer uid, String username, User user)throws UserNotFoundException, UpdateException;
	
	//自定义获取用户信息功能业务层的方法
	User getByUid(Integer uid) throws UserNotFoundException;
}
