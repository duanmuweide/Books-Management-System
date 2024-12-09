package com.libr.entity;
//储存基础用户信息
public class UserInfo {
	private Integer userId;
	private String userName;
	private String userPassword;
	private String userQuestion;
	private String userAnswer;
	private boolean userAdmin;
	private String userGender;
	private String userContact;
	private String userRealname;
	private String userAddress;
	private String userEmail;
	private String userMajor;
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
	public boolean isUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserContact() {
		return userContact;
	}
	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}
	public String getUserRealname() {
		return userRealname;
	}
	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserMajor() {
		return userMajor;
	}
	public void setUserMajor(String userMajor) {
		this.userMajor = userMajor;
	}
	public UserInfo(Integer userId, String userName, String userPassword, String userQuestion, String userAnswer,
			boolean userAdmin, String userGender, String userContact, String userRealname, String userAddress,
			String userEmail, String userMajor) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userQuestion = userQuestion;
		this.userAnswer = userAnswer;
		this.userAdmin = userAdmin;
		this.userGender = userGender;
		this.userContact = userContact;
		this.userRealname = userRealname;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userMajor = userMajor;
	}
	public UserInfo(){}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userQuestion=" + userQuestion + ", userAnswer=" + userAnswer + ", userAdmin=" + userAdmin
				+ ", userGender=" + userGender + ", userContact=" + userContact + ", userRealname=" + userRealname
				+ ", userAddress=" + userAddress + ", userEmail=" + userEmail + ", userMajor=" + userMajor + "]";
	}
	
	
	
	
	
}
