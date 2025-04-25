package cn.tedu.jmstore.service;

import java.util.List;

import cn.tedu.jmstore.entity.Address;
import cn.tedu.jmstore.service.ex.AddressCountLimitException;
import cn.tedu.jmstore.service.ex.InsertException;

public interface IAddressService {
	void createAddress(Integer uid,String username, Address address)throws AddressCountLimitException, InsertException;
	List<Address> listByUid(Integer uid);
}
