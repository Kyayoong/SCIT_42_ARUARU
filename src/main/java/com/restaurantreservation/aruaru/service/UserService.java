package com.restaurantreservation.aruaru.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_zzim;
import com.restaurantreservation.aruaru.domain.Review;
import com.restaurantreservation.aruaru.domain.Usage_history;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.Web_reply;

public interface UserService {
	// 회원가입
	public int insertUser(User_member member);

	// ID 중복확인
	public boolean idcheck(String member_id);

	// 회원 정보 가져옹기
	public User_member selectUser(String member_id);

	// 정보 수정
	public int updateUser(User_member member);

	// 회원 탈퇴
	public int deleteUser(String member_id);
	
	//문의사항에 자기 아이디로 작성한 글만 보이도록 함
	public List<Web_board> findBoard(String username);
	
	//문의사항 등록
	public int insertBoard(Web_board b);

	//문의사항 읽기
	public Web_board readBoard(int board_num);

	// 예약내역 확인
	public ArrayList<Reservation> seeAllReservation(String member_id);
	
	// 지난 예약내역 확인
	public ArrayList<Reservation> seeAllLastReservation(String member_id);

	
	// 찜리스트
	public ArrayList<Restaurant_zzim> mywishlist(String member_id);
	/**
	 * 특정 회원의 이용내역 불러오기
	 * @param 찾을 회원의 id 정보
	 * @return 이용내역 리스트
	 */
	public ArrayList<Usage_history> selectAllUsageHistory(String username);
	
	/**
	 * 특정 이용내역번호의 내역 불러오기
	 * @param 이용내역번호
	 * @return	이용내역객체
	 */
	public Usage_history selectOneUsageHistory(int usageNum);

	public List<Web_reply> readReply(int board_num);

	public List<Web_reply> readReplyAll(int board_num);

	
	/**
	 * 리뷰 객체를 저장한다.
	 * @param review
	 * @return 성공여부
	 */
	public int insertReview(Review review);

	//문의사항 수정
	public int updateBoard(Web_board b);
	
	/**
	 * 로그인 유저의 모든 리뷰 내역을 가져온다
	 * @param username
	 * @return review list
	 */
	public ArrayList<Review> selectAllReview(String username);

	//문의사항 삭제
	public int deleteBoard(int board_num);

	//DB에 등록한 태그 불러옴
	String ownTags(String username);
    
	//태그로 검색했을 때 해당하는 레스토랑의 등록번호
	public List<Integer> recommend(String[] mytags);

	//레스토랑 등록번호를 토대로 한 추천 레스토랑들
	public List<Restaurant_member> recommendStores(int[] stores);
	
	public int updateRole(String member_id);
	
	/**
	 * 공지글 입력
	 * @param notice
	 * @return
	 */
	
	public int insertNoticeBoard(Web_board notice);

	//공지사항 목록 읽어오기
	public List<Web_board> noticeBoard();

	//공지사항 글 읽기
	public Web_board noticeRead(int board_num);

	

}
