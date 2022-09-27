package com.restaurantreservation.aruaru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantreservation.aruaru.dao.UserDao;
import com.restaurantreservation.aruaru.domain.User_member;
import com.restaurantreservation.aruaru.domain.Web_board;
import com.restaurantreservation.aruaru.domain.tabletest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 회원가입
	@Override
	public int insertUser(User_member member) {
		String encodePassword = passwordEncoder.encode(member.getMember_pw());
		member.setMember_pw(encodePassword);
		int result = dao.insertUser(member);
		return result;
	}

	@Override
	public boolean idcheck(String member_id) {
		User_member member = dao.selectOne(member_id);
		return member == null ? true : false;
	}

	@Override
	public User_member selectUser(String member_id) {
		return dao.selectOne(member_id);
	}

	@Override
	public int updateUser(User_member member) {
		if (member.getMember_pw() != null && member.getMember_pw().length() != 0) {
			String encodedPassword = passwordEncoder.encode(member.getMember_pw());
			member.setMember_pw(encodedPassword);
		}
		int result = dao.updateUser(member);
		return result;
	}

	@Override
	public int deleteUser(String member_id) {
		int result = dao.deleteUser(member_id);
		return result;
	}

	@Override
	public List<Web_board> findBoard(String userid) {
		// TODO Auto-generated method stub
		List<Web_board> findById = dao.findBoardById(userid);
		return findById;
	}

	@Override
	public int insertBoard(Web_board b) {
		// TODO Auto-generated method stub
		int result = dao.insertBoard(b);
		return result;
	}

	@Override
	public Web_board readBoard(int board_num) {
		// TODO Auto-generated method stub
		Web_board b = dao.findBoard(board_num);
		return b;
	}


}
