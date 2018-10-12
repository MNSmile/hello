package com.a.conn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	public static String  DRIVERNAME;
	public static String URL;
	public static String USER;
	public static String PASSWORD;
	
	private static Connection conn;
	
	static {
		Properties p = new Properties();  //�½�һ�� Properties ����
		//�� properties �ж�ȡ��ֵ��
		InputStream ism = ConnectionFactory.class.getResourceAsStream("jdbcinfo.properties");
		
		try {
			p.load(ism);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ism != null) 
				ism.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		DRIVERNAME = p.getProperty("drivername");
		URL = p.getProperty("url");
		USER = p.getProperty("user");
		PASSWORD = p.getProperty("password");
		
		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���������ṩһ�������õ����Ӷ���
	 * @return conn
	 */
	public static Connection getConn() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}





