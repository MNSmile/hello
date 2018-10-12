package com.a.dao.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.a.conn.ConnectionFactory;
import com.a.dao.BaseDao;

public class BaseDaoImpl implements BaseDao{
	private Connection conn;
	private Statement s;
	private ResultSet rs;
	/**
	 * �ر��������
	 */
	private void closeAny() {
		try {
			if (rs != null) rs.close();
			if (s != null) s.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ��ɾ��
	 * @param sql
	 * @return Ӱ�������
	 */
	@Override
	public int executeUpdate(String sql) {
		int i = 0; 
		conn = ConnectionFactory.getConn();
		try {
			s = conn.createStatement();
			i = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return i;
	}
	
	/**
	 * ͨ�ò�ѯ����,���ں��б��������ݼ��ϣ�������һ����������������ʵ��ı��С�
	 * @param sql
	 * @param colList
	 * @return
	 */
	@Override
	public List<Map<String,Object>> queryForList(String sql, List<String> colList) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		conn = ConnectionFactory.getConn();
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Map<String,Object> rowMap = new HashMap<String, Object>();
				for (String key: colList) { //��ѭ������֮��һ����¼�ͷ�װ��һ��map��
					String value = rs.getString(key); 
					rowMap.put(key, value);
				}
				resultList.add(rowMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return resultList;
	}
	
	
	/**
	 * ����û�б����ı������Ƕ�������ݲ�ѯ
	 * @param sql
	 * @return ���б����ݵ�List<Map<String,String>>����
	 */
	@Override
	public List<Map<String, Object>> queryForList(String sql) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		conn = ConnectionFactory.getConn();
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql); //ִ��֮��õ�
			//System.out.println(sql);
			while (rs.next()) {
			
				Map<String, Object> rowMap = new HashMap<String, Object>();
				ResultSetMetaData rsm = rs.getMetaData();
				//��ȡ��ѯ���еĸ���
				int countColumn = rsm.getColumnCount();
				//System.out.println(countColumn);
				for (int i = 1; i <= countColumn; i++) {
					String columnName = rsm.getColumnName(i); //�õ��ڼ�������
					Object columnValue = rs.getString(columnName); //�������õ�����
					rowMap.put(columnName, columnValue);
				}
				resultList.add(rowMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return resultList;
	}
	/**
	 * ������ɾ��
	 * @param sqlList
	 * @return Ӱ�������
	 */
	@Override
	public int executeUpdateTrans(List<String> sqlList) {
		int i = 0;
		conn = ConnectionFactory.getConn();
		try {
			//���Զ��ύ��Чȡ��
			conn.setAutoCommit(false);
			s = conn.createStatement();
			for (String sql : sqlList) {
				i += s.executeUpdate(sql);
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				i = 0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return i;
	}


	
	@Override
	public int executeInsert(String sql, String[] generatedColumns) {
		int primaryKey = 0;
		conn = ConnectionFactory.getConn();
		try {
			s = conn.createStatement();
			int i = s.executeUpdate(sql, generatedColumns);
			if (i > 0) { //ִ�гɹ� i > 0 �Ż�ȥ��ȡ����
				rs = s.getGeneratedKeys();
				if (rs.next()) {
					primaryKey = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return primaryKey;
	}


	@Override
	public int executeInsert(String sql) {
		int primaryKey = 0;
		conn = ConnectionFactory.getConn();
		
		try {
			s = conn.createStatement();
			int i = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			if (i > 0) {
				rs = s.getGeneratedKeys();
				if (rs.next()) {
					primaryKey = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAny();
		}
		return primaryKey;
	}
}
