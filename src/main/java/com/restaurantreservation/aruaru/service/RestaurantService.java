package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;
import java.util.Map;

import com.restaurantreservation.aruaru.domain.Holiday;
import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_time;
import com.restaurantreservation.aruaru.domain.Tags;

public interface RestaurantService {
	
	//아이디로 조회하여 식당객체가져오기
	public Restaurant_member selectOne(String Member_id);
	
	//식당번호로 개체가져오기
	public Restaurant_member selectOne1(int restaurant_num);
	
	//식당회원 저장하기
	public int regist1(Restaurant_member member);
	
	//식당파일 저정하기
	public int fileregist(Restaurant_file file);
	
	//파일 조회하기
	public ArrayList<Restaurant_file> fileselect(int restaurant_num);
	
	//식당리스트가져오기
	public ArrayList<Restaurant_member> resList();
	
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
	
	//태그 리스트
	public ArrayList<Tags> tagList(String tags_sector);
	
	//태그 선택
	public Tags tagRead(int tags_num);
	
	//태그 삭제
	public int tagDelete(Tags tag);
	
	//태그 저장
	public int tagInsert(Tags tag);
	
	//공휴일 리스트
	public ArrayList<Holiday> readHoliday();
	
	//가게영업일 가져오기
	public ArrayList<Restaurant_time> searchTime(int restaurant_num);
	
	//가게 태그 가져오기
	public ArrayList<Tags> searchStoreTags(int restaurant_num);
	
	//검색하기
	ArrayList<Restaurant_member> resListSearch(Map<String, String> map);
	
	//파일 가져오기 하나
	public Restaurant_file readFile(int restaurant_file_num);
	
	
}
