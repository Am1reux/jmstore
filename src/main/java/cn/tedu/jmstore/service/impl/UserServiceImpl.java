package cn.tedu.jmstore.service.impl;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.jmstore.entity.User;
import cn.tedu.jmstore.mapper.UserMapper;
import cn.tedu.jmstore.service.IUserService;
import cn.tedu.jmstore.service.ex.InsertException;
import cn.tedu.jmstore.service.ex.PasswordNotMatchException;
import cn.tedu.jmstore.service.ex.UpdateException;
import cn.tedu.jmstore.service.ex.UserNotFoundException;
import cn.tedu.jmstore.service.ex.UsernameDuplicateException;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper mapper;
	
	public void reg(User user) {
		//处理注册功能的业务逻辑
		// 基于user的getUsername()获取用户名
	    String username = user.getUsername();
		//调用数据库层的方法查询该用户
		User result = mapper.findByUsername(user.getUsername());
		//判断查询到的user是否为null	
		if (result != null) {
			//3.如果不为null，则用户名被占用，抛出“用户名已被占用”的异常
			throw new UsernameDuplicateException("用户名已被占用！");
		}
		// 向user中补全数据
	    // TODO 补全盐值 (UUID->通用唯一识别码)
		String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        String password = user.getPassword();
        // 基于盐值对密码进行加密
        String md5Password = getMd5Password(password, salt);
        // 将加密后的密码添加到user中
        user.setPassword(md5Password);
	    // 补全is_delete为0
	    user.setIsDelete(0);

	    Date now = new Date();
	    // 补全4项日志数据
	    user.setCreatedUser(username);
	    user.setCreatedTime(now);
	    user.setModifiedUser(username);
	    user.setModifiedTime(now);
		//如果为null，则说明该用户名可以使用，则调用数据库层方法将用户注册信息添加到数据库
		Integer row = mapper.addnew(user);
		//查看有无注册成功（即看调用数据库层的方法的返回值是否为1）
		if (!row.equals(1)) {
			//不为1，则添加失败，抛出“插入异常”
			throw new InsertException("注册异常，请联系管理员！");
		}
		//为1，则注册成功
		
	}
	
	public User login(String username,String password) throws UserNotFoundException, PasswordNotMatchException{
	    // 获取User中的username
	    // 调用持久层的findByUsername(username) -> User
		User user = mapper.findByUsername(username);
	    // 判断User是否为null
		if (user == null) {
			// 是：抛出 UserNotFoundException
			throw new UserNotFoundException("登录异常！用户名不存在");
		}
		Integer isDelete = user.getIsDelete();
        // 判断isDelete是否为1
        if(isDelete.equals(1)) {
            // 是：抛出 UserNotFoundException
            throw new UserNotFoundException("登录异常！用户名不存在");
        }
        // 获取查询到的盐值
        String salt = user.getSalt();
        // 对用户传入的密码进行加密
        String md5Password = getMd5Password(password, salt);
        // 判断两个密码是否不一致
        if(!md5Password.equals(user.getPassword())) {
            // 是：抛出 PasswordNotMatchException
            throw new PasswordNotMatchException("登录异常！密码不正确");
        }

        // 将盐值设为null
        user.setSalt(null);
        // 将密码设为null
        user.setPassword(null);
        // 将isDelete设为null
        user.setIsDelete(null);
        // 返回user 
        return user;
	}

	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword, String modifiedUser)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		// 使用uid查询用户数据
		User user = mapper.findByUid(uid);
		// 判断返回结果是否为null
		if(user == null) {
			// 是：UserNotFoundException
			throw new UserNotFoundException("修改密码异常！用户数据不存在");
		}

		// 判断isDelete是否为1
		if(user.getIsDelete().equals(1)) {
			// 是：UserNotFoundException
			throw new UserNotFoundException("修改密码异常！用户数据不存在");	
		}

		// 获取查询到的盐值
		String salt = user.getSalt();
		// 对旧密码进行加密
		String oldMd5Password = getMd5Password(oldPassword, salt);
		// 比较加密结果和查询到的用户密码是否不一致
		if(!oldMd5Password.equals(user.getPassword())) {
			// 是：PasswordNotMatchException
			throw new PasswordNotMatchException("修改密码异常！原始密码错误");
		}

		// 对新密码进行加密
		String newMd5Password = getMd5Password(newPassword, salt);
		// 调用updatePassword()更新密码
		Integer row = mapper.updatePassword(uid, newMd5Password, modifiedUser, new Date());
		// 判断受影响的行数是否不为1
		if(!row.equals(1)) {
			// 是：UpdateException
			throw new UpdateException("修改密码异常！请联系管理员");
		}
		
	}
	
	@Override
	public void changeInfo(Integer uid, String username, User user) throws UserNotFoundException, UpdateException {
		// 根据uid查用户信息
		User result=mapper.findByUid(uid);
		// 判断是否返回null
		if(result==null) {
			// 是：UserNotFoundException
			throw new UserNotFoundException("更新用户信息异常！数据不存在");
		}

		// 判断is_delete是否为1
		if(result.getIsDelete().equals(1)) {
			// 是：UserNotFoundException
			throw new UserNotFoundException("更新用户信息异常！数据不存在");
		}

		// 将uid添加到user的uid中
		user.setUid(uid);
		// 将username添加到user的modifiedUser中
		user.setModifiedUser(username);
		// 向user中添加更新时间
		user.setModifiedTime(new Date());
		// 更新用户数据
		// 获取受影响的行数
		Integer row=mapper.updateInfo(user);
		// 判断是否不为1
		if(!row.equals(1)) {
			// 是：UpdateException
			throw new UpdateException("更新用户信息异常！请联系管理员");
		}
	}

	@Override
	public User getByUid(Integer uid) throws UserNotFoundException {
		// 根据uid查询用户信息
		User user=mapper.findByUid(uid);
		// 判断是否返回null
		if(user==null) {
			// 是：UserNotFoundException
			throw new UserNotFoundException("查询用户信息异常！数据不存在");
		}

		// 判断is_delete是否为1
		if(user.getIsDelete().equals(1)) {
			// 是：UserNotFoundException
			throw new UserNotFoundException("查询用户信息异常！数据不存在");
		}
		
		// 将user中不相关的数据设为null
		// (可选)创建一个新的User对象，仅添加需要的属性
		User info=new User();
		info.setUsername(user.getUsername());
		info.setPhone(user.getPhone());
		info.setEmail(user.getEmail());
		info.setGender(user.getGender());
		
		// 返回用户信息(新的User对象)
		return info;
	}
	/**
     * 对密码进行加密
     * @param password 原始密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    private String getMd5Password(String password,String salt) {
        // salt+password+salt 进行3次加密
        String msg = salt + password + salt;
        for(int i = 0; i < 3; i++) {
            msg = DigestUtils.md5DigestAsHex(msg.getBytes());
        }
        return msg;
    }

}
