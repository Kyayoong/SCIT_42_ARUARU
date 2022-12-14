package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.restaurantreservation.aruaru.domain.Holiday;
import com.restaurantreservation.aruaru.domain.Menu;
import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Restaurant_Graphs;
import com.restaurantreservation.aruaru.domain.Restaurant_file;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_time;
import com.restaurantreservation.aruaru.domain.Restaurant_zzim;
import com.restaurantreservation.aruaru.domain.Review;
import com.restaurantreservation.aruaru.domain.Tags;
import com.restaurantreservation.aruaru.domain.Usage_history;

public interface RestaurantService {

	// 아이디로 조회하여 식당객체가져오기
	public Restaurant_member selectOne(String Member_id);

	// 식당번호로 개체가져오기
	public Restaurant_member selectOne1(int restaurant_num);

	// 식당회원 저장하기
	public int regist1(Restaurant_member member);

	// 식당파일 저정하기
	public int fileregist(Restaurant_file file);

	// 파일 조회하기
	public ArrayList<Restaurant_file> fileselect(int restaurant_num);

	// 식당리스트가져오기
	public ArrayList<Restaurant_member> resList();

	// 메뉴 저장하기
	public int insertmenu(Menu menu);

	// 메뉴 전체조회
	public ArrayList<Menu> menucheck(int Restaurant_num);

	// 메뉴 조회
	public Menu readMenu(int menu_num);

	// 메뉴 삭제
	public int menuDel(int menu_num);

	// 식당 수정
	public int Rupdate(Restaurant_member member);

	// 태그 리스트
	public ArrayList<Tags> tagList(String tags_sector);

	// 태그 선택
	public Tags tagRead(int tags_num);

	// 태그 삭제
	public int tagDelete(Tags tag);

	// 태그 저장
	public int tagInsert(Tags tag);

	// 공휴일 리스트
	public ArrayList<Holiday> readHoliday();

	// 가게영업일 가져오기
	public ArrayList<Restaurant_time> searchTime(int restaurant_num);

	// 가게 태그 가져오기
	public ArrayList<Tags> searchStoreTags(int restaurant_num);

	// 검색하기
	ArrayList<Restaurant_member> resListSearch(Map<String, String> map);

	// 파일 가져오기 하나
	public Restaurant_file readFile(int restaurant_file_num);

	// 예약내역 저장하기
	public int reservationInsert(Reservation reservation);

	// 손님수 조정
	public int peopleCount(Restaurant_member member);

	// 예약내역조회
	public ArrayList<Reservation> ReservationList(int restaurant_num);

	// 식당조회
	public int restCheck(Map<String, String> map);

	// 예약조회 하나
	public Reservation reservationSelect(int reservation_num);

	// 예약 업데이트
	public int reservationUpdate(int reservation_num);

	// 예약 취소
	public int cancelReservation(int reservation_num);
	
	// 예약 삭제
	public int reservationDelete(int reservation_num);

	// 이용내역 등록
	public int usageInsert(Usage_history usage);

	// 찜 하기
	public int zzimAdd(Restaurant_zzim zzim);

	// 찜 빼기
	public int zzimDelete(int restaurant_num);
	
	// 랭크로 추천
	public List<Restaurant_member> showByRank();

	
	// 등록순으로 추천
	public List<Restaurant_member> showByRegDate();

  
	public int zzimDelete(Restaurant_zzim zzim);
	
	// 캔슬 리스트
	public ArrayList<Reservation> seeAllCancelReservation(String username);
	
	//찜 숫자
	public int zzimCount(int restaurant_num);
	
	// 찜 숫자
	public int zzimcheck(Restaurant_zzim zzim);
	
	// 식당 삭제
	public int deleteRest(int restaurant_num);
	
	// 시간 입력
	public int inputTime(Restaurant_time time);
	
	/**
	 * 특정 식당의 일일 데이터 (예약, 리뷰, 찜 개수) 가져오기
	 * @param i : 가져올 데이터의 일자
	 * @param restaurant_num : 가져올 식당의 번호
	 * @return
	 */
	public Restaurant_Graphs selectRestaurantData(int i, int restaurant_num);
	
	// 리뷰 리스트
	public ArrayList<Review> reviewAll(int restaurant_num);
	
	// 리뷰 선택
	public Review reviewSelect(int review_num);
	
	/**
	 * 레스토랑 승인 시, Role을 user에서 member로 올려준다
	 * @param member_id
	 * @return
	 */
	public int updateRoleAsMember(String member_id);

	// 평점 업데이트
	public int updateRest(Restaurant_member member);
}
