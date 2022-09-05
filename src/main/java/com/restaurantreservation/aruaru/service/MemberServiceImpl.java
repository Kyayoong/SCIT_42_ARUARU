package com.restaurantreservation.aruaru.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantreservation.aruaru.dao.MemberDao;
import com.restaurantreservation.aruaru.domain.User_member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao dao;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	// 회원가입
	@Override
	public int insertUser(User_member member) {
//		String encodePassword = passwordEncoder.encode(member.getMember_pw());
//		member.setMember_pw(encodePassword);
		int result = dao.insertUser(member);
		return result;
	}
}
