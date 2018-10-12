package com.a.util;

public class RegexUtil {
	
	/** 1.����
	 *  �����пո�
	 *  ��ͷ����Ϊ��ĸ������ 
	 *  2.�˺�
	 *  ��ĸ�����֣��������
	 */
	private static final String PWDREGEX = "[a-zA-Z0-9]+[a-zA-Z0-9]*[a-zA-Z\\d!@#$%^&*_-]*";
	private static final String ACCOUNTREGEX  = "[a-zA-Z]+[a-zA-Z0-9]*[a-zA-Z\\d!@#$%^&*_-]*";
	private static final String BALANCEREGEX = "[\\d](.[\\d]{0,1})*";
	//2019-09-09 10:30
	private static final String SESSIONTIMEREGEX = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}";
	
	public static int pwdRegex(String pwd) {
		if (pwd.length() >= 3) {
			if (!pwd.matches("^.*[\\s]+.*$")) { //���벻�����ո�
				if (pwd.matches(PWDREGEX)) {  
					return 1;
				}
			}
		}
		return 0; //����У��ʧ��
	}
	public static int accountRegex(String account) {
		if (account.length() >= 3 && account.length() <= 8) {
			if (!account.matches("^.*[\\s]+.*$")) {
				if (account.matches(ACCOUNTREGEX)) {
					return 1;
				}
			}
		}
		return 0;
	}
	public static int balanceRegex(String balance) {
		if (balance.matches(BALANCEREGEX)) {
			return 1;
		}
		return 0;
	}
	public static int TimeRegex(String time) {
		if (time.matches(SESSIONTIMEREGEX)) {
			System.out.println("fsl");
			return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
//		String pwd = "! 132"; //�ո�
//		String pwd1 = "@af";
//		String pwd2 = "a@f";
//		String pwd3 = " ";
//		String pwd4 = "111";
//		System.out.println(RegexUtil.rwdRegex(pwd));
//		System.out.println(RegexUtil.rwdRegex(pwd1));
//		System.out.println(RegexUtil.rwdRegex(pwd2));
//		System.out.println(RegexUtil.rwdRegex(pwd3));
//		System.out.println(RegexUtil.rwdRegex(pwd4));
		
//		String balance = "324.";
//		System.out.println("(1)"+RegexUtil.balanceRegex(balance));
		
		String time = "2028-9-9 0:1";
		System.out.println(RegexUtil.TimeRegex(time));
		System.out.println("ʮ�ּ���");
		
		
	}
}
