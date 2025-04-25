package cn.tedu.jmstore.dbtest;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.jmstore.entity.User;
import cn.tedu.jmstore.mapper.UserMapper;
import cn.tedu.jmstore.service.IUserService;
import cn.tedu.jmstore.service.impl.UserServiceImpl;

@SpringBootTest
public class TestUserService {
	
	@Autowired
	private IUserService service;   //注入UserMapper接口对象
	
	//@Test
	public void testReg() {
		try {
	        User user=new User();
	        user.setUsername("Tom");
	        user.setPassword("1234");
	        service.reg(user);
	    } catch (Exception e) {
	        System.err.println(e.getClass().getName());
	        System.err.println(e.getMessage());
	    }
	}
	
	//@Test
	public void login() {
	    try {
	        String username = "tom";
	        String password = "123";
	        User user = service.login(username, password);
	        System.err.println(user);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println(e.getClass().getName());
	        System.err.println(e.getMessage());
	    }
	}
	
	//@Test
	public void changePassword() {
		try {
			Integer uid=13;
			String oldPassword="123";
			String newPassword="456";
			String modifiedUser="管理员";
			service.changePassword(uid, oldPassword, newPassword, modifiedUser);
		}catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changeInfo() {
		try {
			User user=new User();
			user.setPhone("222");
			user.setEmail("555@qq.com");
			user.setGender(1);
			Integer uid=12;
			String modifiedUser="管理员123";
			service.changeInfo(uid, modifiedUser, user);
		}catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void findByUid() {
		try {
			User user=service.getByUid(12);
			System.err.println(user);
		}catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
}
