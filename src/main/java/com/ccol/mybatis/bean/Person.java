package com.ccol.mybatis.bean;

public class Person {
	private int pid;
	private String pname;
	private String psex;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPsex() {
		return psex;
	}
	public void setPsex(String psex) {
		this.psex = psex;
	}
	@Override
	public String toString() {
		return "Person [pid=" + pid + ", pname=" + pname + ", psex=" + psex + "]";
	}
	
	
	
}
