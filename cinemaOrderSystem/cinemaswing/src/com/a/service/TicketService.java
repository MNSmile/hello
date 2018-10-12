package com.a.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TicketService {
	
	/**
	 * ��Ʊ�ķ���
	 * @param userid �û�ID
	 * @param sessionid ����ID
	 * @param ticketSet Ʊ�ļ���
	 * @param price Ʊ����
	 * @return Ӱ������,��������ȥƱ��*Ʊ��
	 */
	public int shoppingTicket(Integer userid, String sessionid, Set<String> ticketSet, Double price);
	
	
	/**
	 * չʾƱ
	 * @param status ״̬����ʷƱ(0)���ǻ�ûʹ�õ�Ʊ(1)
	 * @param userid �û��ɣ�
	 * @return
	 */
	public List<Map<String,Object>> findSessionidByUserId(String status, Integer userid);
	
	/**
	 * ͨ��ƱID�ҵ���Ӧ����ID
	 * @param ticketid
	 */
	public String findSessionidByTicketid(String ticketid);
	
	
	/**
	 * ����ƱΪ��Ʊ
	 */
	public int setTicketStatus0(String ticketid);
	
	/**
	 * �鿴ĳһ�����Ƿ���������Ʊ
	 * @param sessionid ����ID
	 * @return
	 */
	public List<Map<String,Object>> findTicketsBySessionid(Integer sessionid);
}
	