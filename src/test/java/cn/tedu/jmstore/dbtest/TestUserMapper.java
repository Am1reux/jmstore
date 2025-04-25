package cn.tedu.jmstore.dbtest;

import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.jmstore.entity.User;
import cn.tedu.jmstore.mapper.UserMapper;

@SpringBootTest
public class TestUserMapper {
	
	@Autowired
	UserMapper mapper;   //注入UserMapper接口对象
	
	//@Test
	public void testAddnew() {
		User user = new User();
		user.setUsername("tom");
		user.setPassword("123");
		user.setPhone("123124512895");
		mapper.addnew(user);
	}
	
	//@Test
	public void testFindNyUsername() {
		User user = mapper.findByUsername("tom");
		System.err.println(user.getPhone());
	}
	
	//@Test
	public void findByUid() {
		User user=mapper.findByUid(12);
		System.err.println(user);
	}

	//@Test
	public void updatePassword() {
		Integer uid=12;
		String password="123";
		String modifiedUser="炒鸡管理员";
		Date modifiedTime=new Date();
		Integer row=mapper.updatePassword(
				uid, password, modifiedUser, modifiedTime);
		System.err.println("row="+row);
	}
	
	//@Test
	public void updateInfo() {
		User user=new User();
		user.setUid(12);
		user.setPhone("12345678");
		user.setEmail("1234@qq.com");
		user.setGender(1);
		user.setModifiedUser("管理员");
		user.setModifiedTime(new Date());
		Integer row=mapper.updateInfo(user);
		System.err.println("row="+row);
	}
}
