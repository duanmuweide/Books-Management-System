package com.libr.service.impl;

import java.util.List;

import com.libr.dao.impl.BookDaoImpl;
import com.libr.dao.impl.UserInfoDaoImpl;
import com.libr.entity.Book;
import com.libr.entity.UserInfo;
import com.libr.service.UserService;

public class UserServiceImpl implements UserService {

	// 根据id修改密码
	@Override
	public void changePassword(int userId, String newPassword) {
		UserInfoDaoImpl uidi = new UserInfoDaoImpl();
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
		UserInfoDaoImpl uidi = new UserInfoDaoImpl();
		int result = uidi.insert(userInfo);
		if (result > 0) {
			System.out.println("注册成功！");
		} else {
			System.out.println("注册失败！");
		}
	}

	// 登录
	@Override
	public void loginIn(int userId, String password) {
		UserInfoDaoImpl uidi = new UserInfoDaoImpl();
		UserInfo userInfo = uidi.getOneById(userId);
		if (userInfo != null && userInfo.getUserPassword().equals(password)) {
			System.out.println("登录成功！");
		}
		System.out.println("登录失败！用户名或密码错误。");
	}

	// 根据作者寻找书
	@Override
	public void serachBookByWriter(String bookWriterName) {
		BookDaoImpl bdi = new BookDaoImpl();// 创建 BookDaoImpl 对象

		// 调用 getOneByAuthor 方法查询作者的书籍
		List<Book> books = bdi.getOneByAuthor(bookWriterName);

		// 判断是否找到书籍
		if (books.isEmpty()) {
			System.out.println("未找到该作者的书籍！");
		} else {
			System.out.println("找到以下书籍：");
			// 遍历输出书籍信息
			for (Book book : books) {
				System.out.println("书名：" + book.getBookName());
				System.out.println("书籍ID：" + book.getBookId());
				System.out.println("类型：" + book.getBookType());
				System.out.println("库存数量：" + book.getBookNumber());
				System.out.println("书籍状态：" + book.getBookStatement());
				System.out.println("书籍位置：" + book.getBookPosition());
				System.out.println("出版时间：" + book.getBookTime());
				System.out.println("-------------------------");
			}
		}
	}

	// 通过书名寻找
	@Override
	public void serachBookByBookName(String bookName) {
		BookDaoImpl bdi = new BookDaoImpl();// 创建 BookDaoImpl 对象

		// 调用 getOneByBookName 方法查询书籍
		List<Book> books = bdi.getOneByBookName(bookName);

		// 判断是否找到书籍
		if (books.isEmpty()) {
			System.out.println("未找到该书籍！");
		} else {
			System.out.println("找到以下书籍：");
			// 遍历输出书籍信息
			for (Book book : books) {
				System.out.println("书名：" + book.getBookName());
				System.out.println("书籍ID：" + book.getBookId());
				System.out.println("类型：" + book.getBookType());
				System.out.println("库存数量：" + book.getBookNumber());
				System.out.println("书籍状态：" + book.getBookStatement());
				System.out.println("书籍位置：" + book.getBookPosition());
				System.out.println("出版时间：" + book.getBookTime());
				System.out.println("-------------------------");
			}
		}
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