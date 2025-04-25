package cn.tedu.jmstore.dbtest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.tedu.jmstore.entity.District;
import cn.tedu.jmstore.entity.Product;
import cn.tedu.jmstore.service.IDistrictService;
import cn.tedu.jmstore.service.IProductService;
import cn.tedu.jmstore.service.ex.ServiceException;

@SpringBootTest
public class TestProductService {

	@Autowired
    IProductService service;

	//@Test
	public void getHotList() {
	    List<Product> list=service.getHotList();
	    for(Product p:list) {
	        System.err.println(p);
	    }
	}
	
	//@Test
	public void getById() {
	    try {
	        Product product=service.getById(100000425); //换已有商品id 比如100000425 在测试
	        System.err.println(product);
	    } catch (ServiceException e) {
	        System.err.println(e.getClass().getName());
	        System.err.println(e.getMessage());
	    }
	}
	
	//@Test
		public void getSearchList() {
		    List<Product> list=service.getSearchList("联");
		    for(Product p:list) {
		        System.err.println(p);
		    }
		}
		@Test
		public void getNewList() {
			List<Product> list = service.getNewList();
			for (Product p : list) {
				System.err.println(p);
			}
		}
}
