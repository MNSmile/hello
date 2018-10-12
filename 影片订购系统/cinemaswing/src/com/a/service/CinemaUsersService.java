package com.a.service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.a.pojo.CinemaUsers;

public interface CinemaUsersService {
	
	/**
	 * ��¼
	 * @param uaccount �˻���
	 * @param passwd  ����
	 * @param states  ����Ա������ͨ�û�
	 * @return ���� null ���� Map���ϣ�null ��ʾ�˺��������map���ϰ����˻���ϢΪ KEY
	 */
	public Map<String,Object> Login(String uaccount,String passwd, String states);
	
	/**
	 * ע����ͨ�û�
	 * @param cinemausers
	 * @return ���� 
	 */
	public int registUser(CinemaUsers cinemausers);
	
	/**
	 * ��ѯ�û���Ϣ
	 * @param name �û�����
	 * @param account �û��˺�
	 * @return �����û���Ϣ����Vector������Vector�������һ����¼
	 */
	public Vector<Vector> findUserInfo(String name, String account);
	
	/**
	 * ɾ���û�
	 * @param userid
	 * @return Ӱ������
	 */
	public int deleteUserById(Integer userid);
	
	/**
	 * �����û���Ϣ
	 * @param cinemausers �û���װ��
	 * @return Ӱ������
	 */
	public int updateUserInfoById(CinemaUsers cinemausers);
	
	
	/**
	 * ��ʼ���û�����
	 * @param userId �û�ID
	 * @return Ӱ������
	 */
	public int updateUserPwdById(Integer userId);
	
	/**
	 * ͨ���û�ID��ѯ�û���Ϣ
	 * @param userid
	 * @return �û�������Ϣ
	 */
	public List<Map<String,Object>> findUserById(Integer userid);
	
	/**
	 * ͨ���û��˺Ų�ѯ�û�������Ϣ
	 * @param account
	 * @return
	 */
	public List<Map<String,Object>> findUserByAccount(String account);
}
