package com.restaurantreservation.aruaru.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_zzim;
import com.restaurantreservation.aruaru.domain.Review;
import com.restaurantreservation.aruaru.domain.Usage_history;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;

@Mapper
public interface UserDao {
	// 회원가입
	int insertUser(User_member member);

	// ID 개수 확인
	public int countMemberid(String member_id);

	// 정보 가져오기
	User_member selectOne(String member_id);

	// 정보 수정
	public int updateUser(User_member member);

	// 회원 탈퇴
	public int deleteUser(String member_id);

	List<Web_board> findBoardById(String username);

	int insertBoard(Web_board b);

	public Web_board findBoard(int board_num);


	List<Web_reply> readReply(int board_num);


	// 예약내역 확인
	public ArrayList<Reservation> seeAllReservation(String member_id);
	
	// 지난 예약내역 확인
	public ArrayList<Reservation> seeAllLastReservation(String member_id);

	/**
	 * 특정 회원의 이용내역 불러오기
	 * 
	 * @param 찾을 회원의 id 정보
	 * @return 이용내역 리스트
	 */
	ArrayList<Usage_history> selectAllUsageHistory(String username);
	
	/**
	 * 특정 이용내역 받아오기
	 * @param usageNum 이용내역번호
	 * @return 이용내역객체
	 */
	Usage_history selectOneUsageHistory(int usageNum);
	
	/**
	 * 리뷰객체 저장하기
	 * @param review
	 * @return 성공여부
	 */
	int insertReview(Review review);

	int deleteBoard(int board_num);

	/**
	 * 로그인 정보를 통해 해당 유저의 전체 리뷰 가져오기
	 * @param username
	 * @return 리뷰 리스트
	 */
	ArrayList<Review> selectAllReview(String username);

	int updateBoard(Web_board b);

	String myTags(String username);

	List<Integer> recommend(String[] mytags);

	List<Restaurant_member> recommendStores(int[] a);
	
	int updateRole(String member_id);


	List<Web_board> noticeBoard();

	Web_board noticeRead(int board_num);


	
	//공지글 입력
	int insertNoticeBoard(Web_board notice);
  
	ArrayList<Restaurant_zzim> mywishlist(String member_id);
	
	//전체 유저 가져오기
	ArrayList<User_member> selectAllUsers();
	
	//유저 권한 변경(admin창에서)
	int modifyRole(HashMap<Object, Object> map);
}
