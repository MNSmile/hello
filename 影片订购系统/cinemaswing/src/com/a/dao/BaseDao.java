package com.a.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {
	/**
	 * ��ѯ
	 * @param sql
	 * @param colList ��ѯ������
	 * @return 
	 */
	public List<Map<String,Object>> queryForList(String sql, List<String> colList) ;
	
	
	/**
	 * ��ѯ
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql) ;
	
	
	/**
	 * ��ɾ��
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql);
	

	/**
	 * ����ִ��
	 * @param sqlList sql ����
	 * @return
	 */
	public int executeUpdateTrans(List<String> sqlList);
	
	
	/**
	 * ����һ�����ݲ���������ֵ��Oracle��
	 * @param sql
	 * @param generatedColumns Ϊ����������String ����
	 * @return
	 */
	public int executeInsert(String sql, String [] generatedColumns);
	
	
	/**
	 * ����һ�����ݲ���������ֵ��MySQL��
	 * @param sql
	 * @return ����ֵ
	 */
	public int executeInsert(String sql) ;
}
