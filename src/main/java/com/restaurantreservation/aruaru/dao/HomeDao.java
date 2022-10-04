package com.restaurantreservation.aruaru.dao;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.Admin_Graphs;

@Mapper
public interface HomeDao {
	//DB의 일일 추이 데이터 저장값을 올려주는 메소드
	int increaseToday(Admin_Graphs data);
	
	//오늘의 데이터 수치 저장소가 있는지 없는지 확인하는 매퍼
	int checkNewDate();
	
	//새로운 데이터 수치 저장소를 만들어주는 매퍼
	int createNewDate();
	
	/**
	 * 그래프에 표기할 날짜와 데이터를 가져온다.
	 * @param i 오늘:0, 1일전:-1 , .... , 4일전:-4 까지
	 * @return
	 */
	Admin_Graphs selectData(int i);
	
	/**
	 * 해당 날짜의 리뷰 개수를 가져온다(사이트 전체)
	 * @param i 오늘:0, 1일전:-1 , .... , 4일전:-4 까지
	 * @return
	 */
	int selectReviewCntByDate(int i);
	
	/**
	 * 해당 날짜의 일반 가입자 수를 가져온다
	 * @param i 오늘:0, 1일전:-1 , .... , 4일전:-4 까지
	 * @return
	 */
	int selectUserCntByDate(int i);
	
	/**
	 * 해당 날짜의 식당 가입자 수를 가져온다
	 * @param i 오늘:0, 1일전:-1 , .... , 4일전:-4 까지
	 * @return
	 */
	int selectRestaurantCntByDate(int i);
	
}
