package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberDto : user의 정보", description = "user의 상세 정보를 나타낸다.")
public class MemberDto {
	
	@ApiModelProperty(value = "아이디", example = "ssafy2")
	private String userId;
	@ApiModelProperty(value = "비밀번호")
	private String userPwd;
	@ApiModelProperty(value = "이름")
	private String userName;
	@ApiModelProperty(value = "주소")
	private String userAddress;
	@ApiModelProperty(value = "핸드폰 번호")
	private String userPhone;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", userAddress="
				+ userAddress + ", userPhone=" + userPhone + "]";
	}
}