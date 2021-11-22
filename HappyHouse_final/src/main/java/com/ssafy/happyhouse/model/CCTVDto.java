package com.ssafy.happyhouse.model;

public class CCTVDto {

	private int no;
	private String svcareaid;
	private String updtdate;
	private String wgsxpt;
	private String wgsypt;
	private String qty;
	private String addr;
	private String cctvuse;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSvcareaid() {
		return svcareaid;
	}
	public void setSvcareaid(String svcareaid) {
		this.svcareaid = svcareaid;
	}
	public String getUpdtdate() {
		return updtdate;
	}
	public void setUpdtdate(String updtdate) {
		this.updtdate = updtdate;
	}
	public String getWgsxpt() {
		return wgsxpt;
	}
	public void setWgsxpt(String wgsxpt) {
		this.wgsxpt = wgsxpt;
	}
	public String getWgsypt() {
		return wgsypt;
	}
	public void setWgsypt(String wgsypt) {
		this.wgsypt = wgsypt;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCctvuse() {
		return cctvuse;
	}
	public void setCctvuse(String cctvuse) {
		this.cctvuse = cctvuse;
	}
	@Override
	public String toString() {
		return "CCTVDto [no=" + no + ", svcareaid=" + svcareaid + ", updtdate=" + updtdate + ", wgsxpt=" + wgsxpt
				+ ", wgsypt=" + wgsypt + ", qty=" + qty + ", addr=" + addr + ", cctvuse=" + cctvuse + "]";
	}	
}
