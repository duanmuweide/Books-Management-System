package com.libr.entity;
//储存基础用户信息
//我先写着实体类了哈
public class UserInfo {
	private Integer userId;
	private String userName;
	private String userPassword;
	private String userQuestion;
	private String userAnswer;
	private boolean useradmin;
	
	//构造函数
	public UserInfo(Integer userId, String userName, String userPassword, String userQuestion, String userAnswer,
			boolean useradmin) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userQuestion = userQuestion;
		this.userAnswer = userAnswer;
		this.useradmin = useradmin;
	}
	//getter&setter
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserQuestion() {
		return userQuestion;
	}
	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public boolean isUseradmin() {
		return useradmin;
	}
	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}
	
	
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userQuestion=" + userQuestion + ", userAnswer=" + userAnswer + ", useradmin=" + useradmin + "]";
	}
	
	
	
}
