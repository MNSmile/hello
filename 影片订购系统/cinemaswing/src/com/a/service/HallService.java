package com.a.service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.a.pojo.Hall;

public interface HallService {
	
	/**
	 * ���Ӱ��
	 * @param cinemaId ӰԺID
	 * @return Ӱ������
	 */
	public int AddHallByCinemaId(Hall hall);
	
	/**
	 * ��ѯ�Ƿ����hallName��Ӱ��
	 * @param hallName
	 * @return 
	 */
	public int findHallByCinemalId(String hallName);
	/**
	 * ��������Ӱ����Ϣ
	 * @return
	 */
	public Vector<Vector<String>> findAllHall();
	
	
	/**
	 * ͨ��Ӱ��ID����Ӱ������
	 * @param hallid
	 * @return Ӱ������
	 */
	public String findHallCapacityById(String hallid);
	
	/**
	 * ��ѯĳһӰԺ����Ӱ����Ϣ
	 * @param cinemaId
	 * @return
	 */
	public Vector<Vector<String>> findAllHallByCinemaId(Integer cinemaId);
	
	/**
	 * ɾ��Ӱ��
	 * @param hallid
	 * @return
	 */
	public int deleteHallById(Integer hallid);
}
