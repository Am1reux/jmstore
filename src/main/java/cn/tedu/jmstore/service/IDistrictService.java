package cn.tedu.jmstore.service;

import java.util.List;

import cn.tedu.jmstore.entity.District;

public interface IDistrictService {
	List<District> listByParent(String parent);
	District getByCode(String code);
}
