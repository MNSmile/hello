package com.a.service;

import java.util.Vector;

import com.a.pojo.Cinema;

public interface CinemaService {
	
	/**
	 * ��ѯӰԺ
	 * @param cinema
	 * @return ����ӰԺ��Ϣ
	 */
	public Vector<Vector<String>> findCinema(Cinema cinema);
	
	/**
	 * ���ӰԺ
	 * @param cinema
	 * @return
	 */
	public int AddCinema(Cinema cinema);
	
	/**
	 * ɾ��ӰԺ
	 * @param cinemaId 
	 * @return Ӱ������
	 */
	public int deleteCinema(Integer cinemaId);
	
	
}
