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
<<<<<<< HEAD

	//获取密码
	String getPassword(int id);
=======
>>>>>>> 1e73c4f3e56b3e606a9a17dca969424616b56166
}