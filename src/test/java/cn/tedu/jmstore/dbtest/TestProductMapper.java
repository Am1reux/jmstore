package cn.tedu.jmstore.dbtest;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.jmstore.entity.Product;
import cn.tedu.jmstore.mapper.ProductMapper;

@SpringBootTest
public class TestProductMapper {
	
	@Autowired
	ProductMapper mapper;   //注入UserMapper接口对象
	
	//@Test
	public void findHotList() {
	    List<Product> list = mapper.findHotList();
	    for(Product p:list) {
	        System.err.println(p);
	    }
	}
	
	//@Test
	public void findById() {
	    System.err.println(mapper.findById(10000001));
	}
	
	//@Test
	public void SearchProductList() {
	    List<Product> list = mapper.SearchProductList("联");
	    for(Product p:list) {
	        System.err.println(p);
	    }
	}
	
	@Test
	public void findNewList() {
		List<Product> list = mapper.findNewList();
		for (Product p : list) {
			System.err.println(p);
		}
	}
}
