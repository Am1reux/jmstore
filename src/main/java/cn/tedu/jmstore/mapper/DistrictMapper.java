package cn.tedu.jmstore.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.tedu.jmstore.entity.District;
@Mapper
public interface DistrictMapper {
	List<District> findByParent(String parent);
	District findByCode(String code);
}
