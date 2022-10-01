package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantreservation.aruaru.dao.RestaurantDao;
import com.restaurantreservation.aruaru.domain.Holiday;
import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_time;
import com.restaurantreservation.aruaru.domain.Restaurant_zzim;
import com.restaurantreservation.aruaru.domain.Tags;
import com.restaurantreservation.aruaru.domain.Usage_history;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantDao dao;

	// 아이디로 조회하여 식당객체가져오기
	@Override
	public Restaurant_member selectOne(String Member_id) {
		Restaurant_member member = dao.selectOne(Member_id);
		return member;
	}
	// 번호로 식당개체가져오기
	@Override
	public Restaurant_member selectOne1(int restaurant_num) {
		Restaurant_member member = dao.selectOne1(restaurant_num);
		return member;
	}

	// 식당회원 저장하기
	@Override
	public int regist1(Restaurant_member member) {
		int result = dao.regist1(member);
		return result;
	}

	// 식당파일 저정하기
	@Override
	public int fileregist(Restaurant_file file) {
		int result = dao.fileregist(file);
		return result;
	}
	
	// 식당파일 조회하기
	@Override
	public ArrayList<Restaurant_file> fileselect(int restaurant_num) {
		ArrayList<Restaurant_file> fileList = dao.fileselect(restaurant_num);
		return fileList;
	}
	
	// 식당 리스트 조회하기
	@Override
	public ArrayList<Restaurant_member> resList() {
		// TODO Auto-generated method stub
		ArrayList<Restaurant_member> resList = dao.resList();
		return resList;
	}
	// 메뉴 저장하기
	@Override
	public int insertmenu(Menu menu) {
		int result = dao.insertmenu(menu);
		return result;
	}
	// 메뉴 전체 조회
	@Override
	public ArrayList<Menu> menucheck(int Restaurant_num) {
		ArrayList<Menu> list = dao.menucheck(Restaurant_num);
		return list;
	}
	// 메뉴 조회
	@Override
	public Menu readMenu(int menu_num) {
		Menu menu = dao.readMenu(menu_num);
		return menu;
	}
	// 메뉴 삭제
	@Override
	public int menuDel(int menu_num) {
		int result = dao.menuDel(menu_num);
		return result;
	}

	// 식당수정
	@Override
	public int Rupdate(Restaurant_member member) {
		int result = dao.Rupdate(member);
		return result;
	}
	// 태그리스트
	@Override
	public ArrayList<Tags> tagList(String tags_sector) {
		ArrayList<Tags> taglist = dao.tagList(tags_sector);
		return taglist;
	}

	// 태그 조회
	@Override
	public Tags tagRead(int tags_num) {
		Tags tag = dao.tagRead(tags_num);
		return tag;
	}
	// 태그 저장
	@Override
	public int tagInsert(Tags tag) {
		int result = dao.tagInsert(tag);
		return result;
	}
	// 태그 삭제
	@Override
	public int tagDelete(Tags tag) {
		int result = dao.tagDelete(tag);
		return result;
	}
	//휴무일
	@Override
	public ArrayList<Holiday> readHoliday() {
		ArrayList<Holiday> hList = dao.readHoliday();
		return hList;
	}
	//영업일가져오기
	@Override
	public ArrayList<Restaurant_time>searchTime(int restaurant_num) {
		ArrayList<Restaurant_time> timeTable = dao.readTime(restaurant_num);
		return timeTable;
	}
	//가게태그가져오기
	@Override
	public ArrayList<Tags> searchStoreTags(int restaurant_num) {
		ArrayList<Tags> storeTags = dao.searchStoreTags(restaurant_num);
		return storeTags;
	}
	//가개리스트 검색
	@Override
	public ArrayList<Restaurant_member> resListSearch(Map<String, String> map) {
		ArrayList<Restaurant_member> resList = dao.resListSearch(map);
		return resList;
		
	}
	//파일 불러오기
	@Override
	public Restaurant_file readFile(int restaurant_file_num) {
		Restaurant_file file = dao.readFile(restaurant_file_num);
		return file;
	}
	//예약입력
	@Override
	public int reservationInsert(Reservation reservation) {
		int result = dao.reservationInsert(reservation);
		return result;
	}
	
	//식당인수 변경
	@Override
	public int peopleCount(Restaurant_member member) {
		int result = dao.peopleCount(member);
		return result;
	}
	//예약내역조회
	@Override
	public ArrayList<Reservation> ReservationList(int restaurant_num) {
		ArrayList<Reservation> list = dao.ReservationList(restaurant_num);
		
		return list;
	}
	@Override
	public int restCheck(Map<String, String> map) {
		int result = dao.restCheck(map);
		return result;
	}
	@Override
	public Reservation reservationSelect(int reservation_num) {
		Reservation rseult = dao.reservationSelect(reservation_num);
		return rseult;
	}
	@Override
	public int reservationUpdate(int reservation_num) {
		int rseult = dao.reservationUpdate(reservation_num);
		return rseult;
	}
	@Override
	public int reservationDelete(int reservation_num) {
		int result = dao.reservationDelete(reservation_num);
		return result;
	}
	@Override
	public int usageInsert(Usage_history usage) {
		int result = dao.usageInsert(usage);
		return result;
	}
	@Override
	public int zzimAdd(Restaurant_zzim zzim) {
		int result = dao.zzimAdd(zzim);
		return result;
	}
	@Override
	public int zzimDelete(int restaurant_num) {
		int result = dao.zzimDelete(restaurant_num);
		return result;
	}
}
