package com.a.pojo;

public class CinemaUsers {
	private Integer userid ; //ID
	private String name ;	 //����
	private String passwd;	 //����
	private String levels;	 //�ȼ�
	private Double balance	;//���	
	private String uaccount;//�˻���
	private String states;	 //״̬����ͨ�û����߹���Ա��
	private String status;   //�û��Ƿ�ע����0��ʾע����1��ʾ����
	public CinemaUsers() {}
	
	public CinemaUsers(Integer userid, String name, String passwd, String levels, Double balance, String uaccount,
			String states) {
		this.userid = userid;
		this.name = name;
		this.passwd = passwd;
		this.levels = levels;
		this.balance = balance;
		this.uaccount = uaccount;
		this.states = states;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getUaccount() {
		return uaccount;
	}

	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CinemaUsers [userid=" + userid + ", name=" + name + ", passwd=" + passwd + ", levels=" + levels
				+ ", balance=" + balance + ", uaccount=" + uaccount + ", states=" + states + "]";
	}
	
}
