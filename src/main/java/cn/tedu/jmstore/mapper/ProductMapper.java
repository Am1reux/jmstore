package cn.tedu.jmstore.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.tedu.jmstore.entity.Product;

@Mapper
public interface ProductMapper {
	List<Product> findHotList();
	Product findById(Integer id);
	List<Product> SearchProductList(String productName);
	// 查询新上架商品
	List<Product> findNewList();
}

