package cn.tedu.jmstore.service;

import java.util.List;

import cn.tedu.jmstore.entity.Product;

public interface IProductService {
	List<Product> getHotList();
	List<Product> getNewList();
	Product getById(Integer id);
	List<Product> getSearchList(String productName);
	
}
