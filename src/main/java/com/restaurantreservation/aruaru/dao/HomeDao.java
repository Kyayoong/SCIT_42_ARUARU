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
	
}
