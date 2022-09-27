package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Web_board {
	int board_num;				//게시판번호
	String member_id;			//회원아이디
	String board_title;			//게시판 제목
	String board_contents;		//게시판 내용
	int hits;					//조회수
	String board_originalfile;	//게시판파일(오리지날)
	String board_savedfile;		//게시판파일(저장)
	int board_suggestion;		//추천수
	String registration_date;	//등록일
	int board_notice;			//공지여부
	String category;			//카테고리
	String member_role;			//작성자 정보(일반?식당?)
}
