package cn.tedu.jmstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.jmstore.entity.Product;
import cn.tedu.jmstore.service.IProductService;
import cn.tedu.jmstore.util.JsonResult;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired 
    IProductService service;

    @GetMapping("hot")
    public JsonResult<List<Product>> getHotList(){
        // 查询
        List<Product> data = service.getHotList();
        // 返回
        return new JsonResult<List<Product>>(SUCCESS, data);
    }
    
    @GetMapping("new")
	public JsonResult<List<Product>> getNewList() {
		// 查询
		List<Product> data = service.getNewList();
		// 返回
		return new JsonResult<List<Product>>(SUCCESS, data);
	}
    
    @GetMapping("{id}/get")
    public JsonResult<Product> getById(@PathVariable("id")Integer id){
        Product product=service.getById(id);
        return new JsonResult<Product>(20,product);
    }
    
    @RequestMapping("{search}/get")
    public JsonResult<List<Product>> getSearchList(@PathVariable("search")String productName){
    	System.out.println(productName);
        // 查询
        List<Product> data = service.getSearchList(productName);
        // 返回
        return new JsonResult<List<Product>>(SUCCESS, data);
    }
//    public JsonResult<List<Product>> getSearchList(String productName){
//        // 查询
//        List<Product> data = service.getSearchList(productName);
//        // 返回
//        return new JsonResult<List<Product>>(SUCCESS, data);
//    }
}