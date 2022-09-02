package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Web_reply {
	
	
	int reply_num;				//댓글번호
	String member_id;			//회원아이디
	int board_num;				//게시판번호
	String reply_contents;		//댓글 내용
	String registration_date;	//등록일
}
