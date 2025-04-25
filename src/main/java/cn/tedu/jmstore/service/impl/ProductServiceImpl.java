package cn.tedu.jmstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.jmstore.entity.Product;
import cn.tedu.jmstore.mapper.ProductMapper;
import cn.tedu.jmstore.service.IProductService;
import cn.tedu.jmstore.service.ex.ProductNotFoundException;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    ProductMapper mapper;
    
    private List<Product> findHotList(){
        return mapper.findHotList();
    }
    
    @Override
    public List<Product> getHotList() {
        return findHotList();
    }
    
    private List<Product> findNewList() {
		return mapper.findNewList();
	}

	public List<Product> getNewList() {
		return findNewList();
	}
    
    private Product findById(Integer id) {
        return mapper.findById(id);
    }
    
    @Override
    public Product getById(Integer id) {


        // 使用id查商品数据
        Product product=findById(id);
        if(product==null) {
            throw new ProductNotFoundException("显示商品详情异常！未找到商品数据");
        }

        // 将不需要给用户的数据设为null
        product.setPriority(null);
        product.setStatus(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }
    
    private List<Product> SearchProductList(String productName){
        return mapper.SearchProductList(productName);
    }
    
    @Override
    public List<Product> getSearchList(String productName) {
        return SearchProductList(productName);
    }
    
	
}
