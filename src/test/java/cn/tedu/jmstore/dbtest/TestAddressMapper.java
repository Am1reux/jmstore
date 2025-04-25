package cn.tedu.jmstore.dbtest;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.jmstore.entity.Address;
import cn.tedu.jmstore.mapper.AddressMapper;

@SpringBootTest
public class TestAddressMapper {
	
	@Autowired   //注入AddressMapper接口对象
	AddressMapper mapper;
	
	//@Test
	public void saveAddress() {
		Address add = new Address();
		add.setUid(12);
		add.setName("小明同学2");;
		Integer row = mapper.saveAddress(add);
		System.err.println("row="+row);
	}
	
	//@Test
	public void countByUid() {
		Integer count = mapper.countByUid(12);
		System.err.println("count="+count);
	}
	
	@Test
	public void findByUid() {
	    List<Address> list=mapper.findByUid(11);//注意这里的uid根据自己的t_user和t_address数据选择合适的uid测试
	    for(Address address:list) {
	        System.err.println(address);
	    }
	}
}

