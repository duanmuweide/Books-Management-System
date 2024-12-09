package com.libr.service.impl;

import com.libr.dao.UserInfoDao;
import com.libr.entity.UserInfo;
import com.libr.service.UserService;

public class UserServiceImpl implements UserService {

	// 根据id修改密码
	@Override
	    public void changePassword(int newPassword) {
		UserInfo userInfo = UserInfoDao.getOneById(String.valueOf(userId));
        if (userInfo != null) {
            userInfo.setUserPassword(newPassword);
            UserInfoDao.update(userInfo);
            System.out.println("密码修改成功！");
        } else {
            System.out.println("用户不存在！");
        }
    }

	// 注册
	@Override
	public void register() {
		
	}

	//登录
	@Override
	public void loginIn() {
		// TODO Auto-generated method stub

	}
	//改变信息
	@Override
	public void changeUserInfo(int userPassword) {
		// TODO Auto-generated method stub

	}
	//根据作者寻找书
	@Override
	public void serachBookByWriter(String bookWriterName) {
		// TODO Auto-generated method stub

	}
	//通过书名寻找
	@Override
	public void serachBookByBookName(String bookName) {
		// TODO Auto-generated method stub

	}
	//寻找借书记录
	@Override
	public void serachBorrowRecord() {
		// TODO Auto-generated method stub

	}
	//寻找还书记录
	@Override
	public void serachReturnRecord() {
		// TODO Auto-generated method stub

	}
}