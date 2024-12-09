package com.libr.service.impl;

import com.libr.dao.UserInfoDao;
import com.libr.dao.impl.UserInfoDaoImpl;
import com.libr.entity.UserInfo;
import com.libr.service.UserService;

public class UserServiceImpl implements UserService {
	
	// 根据id修改密码
	@Override
	public void changePassword(int userId, String newPassword) {
		UserInfoDaoImpl uidi=new UserInfoDaoImpl();
		UserInfo userInfo = uidi.getOneById(userId);
		if (userInfo != null) {
			userInfo.setUserPassword(newPassword);
			uidi.update(userInfo);
			System.out.println("密码修改成功！");
		} else {
			System.out.println("用户不存在！");
		}
	}

	  // 注册
    @Override
    public void register(UserInfo userInfo) {
    	UserInfoDaoImpl uidi=new UserInfoDaoImpl();
        int result = uidi.insert(userInfo);
        if (result > 0) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败！");
        }
    }

	// 登录
	@Override
	public void loginIn(String ) {
		

	}

	// 根据作者寻找书
	@Override
	public void serachBookByWriter(String bookWriterName) {
		// TODO Auto-generated method stub

	}

	// 通过书名寻找
	@Override
	public void serachBookByBookName(String bookName) {
		// TODO Auto-generated method stub

	}

	// 寻找借书记录
	@Override
	public void serachBorrowRecord() {
		// TODO Auto-generated method stub

	}

	// 寻找还书记录
	@Override
	public void serachReturnRecord() {
		// TODO Auto-generated method stub

	}

	// 修改个人信息
	@Override
	public void changeUserInfo(UserInfo user) {
		// TODO Auto-generated method stub

	}
}