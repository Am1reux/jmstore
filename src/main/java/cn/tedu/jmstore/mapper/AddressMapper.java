package cn.tedu.jmstore.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.tedu.jmstore.entity.Address;

@Mapper
public interface AddressMapper {
	Integer saveAddress(Address address);

	Integer countByUid(Integer uid);
	
	List<Address> findByUid(Integer uid);
}
