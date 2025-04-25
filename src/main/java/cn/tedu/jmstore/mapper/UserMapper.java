package cn.tedu.jmstore.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.tedu.jmstore.entity.User;

@Mapper
public interface UserMapper {
	//根据用户名查询用户
	User findByUsername(String username);
	//将用户信息添加到t_user表中
	Integer addnew(User user);
	//根据用户id查询用户
	User findByUid(Integer uid);
	//修改密码
	Integer updatePassword(
		@Param("uid") Integer uid,
		@Param("password") String password,
		@Param("modifiedUser") String modifiedUser,
		@Param("modifiedTime") Date modifiedTime);
	//更新用户信息
	Integer updateInfo(User user);
}
