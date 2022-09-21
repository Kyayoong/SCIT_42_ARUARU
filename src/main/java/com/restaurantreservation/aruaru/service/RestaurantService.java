package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;

import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Tags;

public interface RestaurantService {
	
	//아이디로 조회하여 식당객체가져오기
	public Restaurant_member selectOne(String Member_id);
	
	//식당회원 저장하기
	public int regist1(Restaurant_member member);
	
	//식당파일 저정하기
	public int fileregist(Restaurant_file file);
	
	//메뉴 저장하기
	public int insertmenu(Menu menu);
	
	//메뉴 전체조회
	public ArrayList<Menu> menucheck(int Restaurant_num);
	
	//메뉴 조회
	public Menu readMenu(int menu_num);
	
	//메뉴 삭제
	public int menuDel(int menu_num);
	
	//식당 수정
	public int Rupdate(Restaurant_member member);

	public ArrayList<Tags> tagList(String tags_sector);
	
	

}
