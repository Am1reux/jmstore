package cn.tedu.jmstore.dbtest;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.jmstore.entity.Address;
import cn.tedu.jmstore.entity.User;
import cn.tedu.jmstore.mapper.AddressMapper;
import cn.tedu.jmstore.mapper.UserMapper;
import cn.tedu.jmstore.service.IAddressService;
import cn.tedu.jmstore.service.ex.ServiceException;
import cn.tedu.jmstore.service.impl.AddressServiceImpl;

@SpringBootTest
public class TestAddressService {
	
	@Autowired
	AddressServiceImpl service;
	
	//@Test
	public void createAddress() {
		try {
			Address add=new Address();
			add.setName("小张同学");
			add.setAddress("程序员之家");
			service.createAddress(13, "管理员", add);
		}catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void listByUid() {
	    List<Address> list = service.listByUid(11);
	    for(Address addr:list) {
	        System.err.println(addr);
	    }
	}
}

