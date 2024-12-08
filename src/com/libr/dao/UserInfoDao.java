package com.libr.dao;

import java.util.List;

import com.libr.entity.UserInfo;

public interface UserInfoDao extends BaseDao {

	int insert(UserInfo p);
	
	int update(UserInfo p);
	
	int deleteById(String id);
	
	UserInfo getOneById(String id);
	
	List<UserInfo> getAll();
	
	/**
	 * 根据名称关键字查询产品列表
	 * @param keyword 检索关键字
	 * @return 一个满足查询条件的产品列表
	 */
	List<UserInfo> getAllByKeyword(String keyword);
}