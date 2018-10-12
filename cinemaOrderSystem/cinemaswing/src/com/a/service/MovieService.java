package com.a.service;

import java.util.Vector;

import com.a.pojo.Movie;

public interface MovieService {
	
	/**
	 * ��ӵ�Ӱ
	 * @param movie ����
	 * @return ����
	 */
	public int addMovieReturnId(Movie movie);
	
	/**
	 * �������е�Ӱ
	 * @return 
	 */
	public Vector<Vector<String>> findAllMovies();
	
	/**
	 * ͨ����ӰID�ҵ���Ӱʱ��
	 * @param movieId
	 * @return
	 */
	public String findMovieDurationByMovieId(Integer movieId); 
	
	/**
	 * �Ƚϵ�Ӱ��ӳʱ���Ⱥ�
	 * @param time1 ��ǰӰ����ӳ��Ӱ���ʱ��
	 * @param time2 ��Ƭ��ӳʱ��
	 * @param duration ��ǰӰ����ӳ��Ӱʱ��
	 * @return
	 */
	public boolean compareDate(String time1, String time2, String duration);
}
