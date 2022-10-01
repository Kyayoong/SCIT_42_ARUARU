package com.restaurantreservation.aruaru.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurantreservation.aruaru.domain.Reservation;
import com.restaurantreservation.aruaru.domain.Restaurant_member;
import com.restaurantreservation.aruaru.domain.Restaurant_zzim;
import com.restaurantreservation.aruaru.domain.Usage_history;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.service.RestaurantService;
import com.restaurantreservation.aruaru.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("mypage")
@Controller
public class MyPageRestController {
	@Autowired
	UserService service;
	
	@Autowired
	RestaurantService service1;

	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@ResponseBody
	@PostMapping("rservationList")
	public ArrayList<Reservation> rservationList(@AuthenticationPrincipal UserDetails user) {
		
		Restaurant_member member = service1.selectOne(user.getUsername());
		
		log.debug("{}",member);
		ArrayList<Reservation> rservationList = service1.ReservationList(member.getRestaurant_num());
		log.debug("{}",rservationList);
		return rservationList;
	}
	
	@ResponseBody
	@GetMapping("usageInsert")
	public void usageInsert(@RequestParam int reservation_num,Usage_history usage) {
		log.debug("reservation_num : {}",reservation_num);
		Reservation res = service1.reservationSelect(reservation_num);
		int result =  service1.reservationUpdate(reservation_num);
		log.debug("객체 : {}",res);
		String s = res.getReservation_people() + "인" + res.getReservation_hours();
		log.debug("{}",res.getMember_id());
		usage.setMember_id(res.getMember_id());
		usage.setReservation_num(reservation_num);
		usage.setRestaurant_name(res.getRestaurant_name());
		usage.setRestaurant_num(res.getRestaurant_num());
		usage.setUsage_date(res.getReservation_date());
		usage.setUsage_information(s);
		int result2 = service1.usageInsert(usage);
		
		log.debug("update 결과 : {}",result);
		
		log.debug("insert 결과 : {}",result2);
		
	}

	@ResponseBody
	@GetMapping("reservationDelete")
	public void reservationDelete(@RequestParam int reservation_num) {
		log.debug("reservation_num : {}",reservation_num);
		
		int result =  service1.reservationDelete(reservation_num);
		
		log.debug("delete 결과 : {}",result);
	}
	

}
