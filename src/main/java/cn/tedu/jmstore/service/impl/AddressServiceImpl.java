package cn.tedu.jmstore.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.jmstore.entity.Address;
import cn.tedu.jmstore.entity.District;
import cn.tedu.jmstore.mapper.AddressMapper;
import cn.tedu.jmstore.service.IAddressService;
import cn.tedu.jmstore.service.IDistrictService;
import cn.tedu.jmstore.service.ex.AddressCountLimitException;
import cn.tedu.jmstore.service.ex.InsertException;

@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired
	AddressMapper mapper;
	
	@Autowired 
	IDistrictService service;
	
	private Integer countByUid(Integer uid){
		// 对参数的合理性进行判断	
		if(uid==null || uid<1){
			throw new IllegalArgumentException();
		}
		return mapper.countByUid(uid);
	}

	private void saveAddress(Address address){
		Integer row=mapper.saveAddress(address);
		if(row!=1){
			throw new InsertException("添加收货地址异常！请联系管理员");
		}
	}
	
	public void createAddress(Integer uid, String username, Address address)
			throws AddressCountLimitException, InsertException {
		/*根据uid查询收货地址条数，自己将此查询功能封装成一个方法countByUid，如下笔记所述*/
		Integer count = countByUid(uid);  
		// 条数是否达到上限 3
		if(count >= 3) {
			// 是：AddressCountLimitException
			throw new AddressCountLimitException("新增收货地址异常！最大收货地址条数为"+3);
		}

		// 补全uid
		address.setUid(uid);
		// 补全isDefault，根据上面查询到的收货地址条数进行判断
		int isDefault=count==0 ? 1 : 0;
		address.setIsDefault(isDefault);
		// 补全省市区数据：补充省市区名称
	    String provinceName=getNameByCode(address.getProvinceCode().toString());
	    String cityName=getNameByCode(address.getCityCode().toString());
	    String areaName=getNameByCode(address.getAreaCode().toString());
	    address.setProvinceName(provinceName);
	    address.setCityName(cityName);
	    address.setAreaName(areaName);

		// 创建当前时间对象
		Date now =new Date();
		// 补全4项日志数据
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);
		/* 执行添加操作  (自己将 把地址添加到数据库的操作 封装成功一个方法saveAddress，如下笔记 )*/
		saveAddress(address);
	}
	
	public String getNameByCode(String code){
	    District dist = service.getByCode(code);
	    return dist==null?"":dist.getName();
	}
	
	private List<Address> findByUid(Integer uid){
	    List<Address> list = mapper.findByUid(uid);
	    for(Address addr:list){
	        addr.setZip(null);
	        addr.setTel(null);
	        // 将4项日志数据设为null
	    }
	    return list;
	}
	
	public List<Address> listByUid(Integer uid){
	    return findByUid(uid);
	}
}
