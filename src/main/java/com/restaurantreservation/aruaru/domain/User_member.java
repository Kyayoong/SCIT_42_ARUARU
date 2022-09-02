package com.restaurantreservation.aruaru.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class User_member {

	
	int member_num;				//회원번호
	String member_id;			//회원 아이디
	String member_pw;			//회원 비밀번호
	String member_birth; 		//회원 생년월일
	String member_name;			//회원이름
	String member_phone;		//회원폰번호
	int member_grade;			//회원등급
	String member_role;			//회원역할
	String member_email; 		//이메일
	int email_auth; 			//이메일 인증여부
	String email_key;			//이메일 인증번호
	String registration_date;	//등록일
	String member_address;		//회원주소
	int member_score;			//회원점수
	String member_tags;			//태그번호
	String member_gender; 		//회원 성별
}
