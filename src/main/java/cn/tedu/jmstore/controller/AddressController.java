package cn.tedu.jmstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.jmstore.entity.Address;
import cn.tedu.jmstore.service.IAddressService;
import cn.tedu.jmstore.util.JsonResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
	
	@Autowired
	IAddressService service;

	@RequestMapping("create_address")
	public JsonResult<Void> createAddress(Address address,HttpSession session){
		// 获取用户的uid
		Integer uid=getUidFromSession(session);
		// 获取用户的username
		String username=getUsernameFromSession(session);
		// 执行添加操作
		service.createAddress(uid, username, address);
		// 返回JsonResult对象
		return new JsonResult<>(SUCCESS);
	}
	
	@GetMapping("list")
	public JsonResult<List<Address>> listByUid(HttpSession session){
	    Integer uid=getUidFromSession(session);
	    List<Address> data=service.listByUid(uid);
	    return new JsonResult<>(SUCCESS,data);
	}
}
