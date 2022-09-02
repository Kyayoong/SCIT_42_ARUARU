package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Declaration {

	
    int declaration_num;				//신고번호
    String member_id;					//신고한 회원아이디
    String declaration_id;				//신고받은 회원아이디
    String request;						//신고내용
}
