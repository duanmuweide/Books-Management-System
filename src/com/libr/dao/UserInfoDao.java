package com.libr.dao;

import java.util.List;

import com.libr.entity.UserInfo;

public interface UserInfoDao extends BaseDao {

	int insert(UserInfo p);
	
	int update(UserInfo p);
	
	int deleteById(String id);
	
	UserInfo getOneById(String id);
	
	List<UserInfo> getAll();
	
	
	List<UserInfo> getAllByKeyword(String keyword);
}