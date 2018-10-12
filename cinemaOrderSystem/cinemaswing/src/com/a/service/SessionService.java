package com.a.service;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.a.pojo.Session;

public interface SessionService {
	/**
	 * ��Ƭ
	 * @param s
	 * @return Ӱ������
	 */
	public int addSession(Session s);
	
	/**
	 * �ӳ��α��в��������Ƭ��Ӱ�������һ����Ӱ��ʱ��,��ӰID,����ID
	 * @param hid
	 * @param cid
	 * @param sid
	 * @return ���һ����Ӱ����ʱ��͵�Ӱid
	 */
	public Map<String,Object> findLatestMovieSessionTime(Integer hid, Integer cid);
	
	/**
	 * ��ѯӰѶ
	 * @return
	 */
	public Vector<Vector<String>> findAnyMovieInfo(String movieName, String cinemaName);
	
	/**
	 * ͨ������ID���ҵ�Ӱ
	 * @param sessionid
	 * @return 
	 */
	public Map<String,Object> findMovieInfoBySessionId(String sessionid);
	
	
	/**
	 * ͨ������ID����ĳһ��Ӱ�Ѿ�ѡ�˵���λ
	 * @param sessionId ����id
	 * @return ��λ�ż���
	 */
	public Set<String> findSeatSetBySessionId(String sessionId);
	
	
	/**
	 * ����ĳһ��������
	 * @param sessionid
	 * @return Ӱ������
	 */
	public int updateSeatRemainBySessionId(String sessionid, int seatSum);
	
	
	/**
	 * ͨ������id,�õ�ĳ�˹�Ʊ�ĵ�Ӱ����ӰԺ��Ӱ������λ�š���ӳʱ�䡢ʱ��������(�鵽�Ķ��Ƿ�ӳʱ�����ϵͳʱ��)
	 * @param sessionid ����ID
	 * @param status ״̬
	 * @param uid �û�id
	 * @return 
	 */
	public Vector<Vector<String>> findMovieRelatedInformationBySessionid(String sessionid, String status, Integer uid);

}




