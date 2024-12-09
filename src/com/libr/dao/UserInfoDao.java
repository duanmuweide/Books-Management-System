package com.libr.dao;

import java.util.List;

import com.libr.entity.UserInfo;

public interface UserInfoDao extends BaseDao {

	int insert(UserInfo p);
	
	int update(UserInfo p);
	
	int deleteById(int id);
	
	UserInfo getOneById(int id);
	
	List<UserInfo> getAll();
	
	
	List<UserInfo> getAllByKeyword(String keyword);

	//获取密码
	String getPassword(int id);
}