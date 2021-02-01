package com.cos.project.service;

import com.cos.project.domain.user.User;
import com.cos.project.domain.user.UserDao;
import com.cos.project.domain.user.dto.JoinReqDto;
import com.cos.project.domain.user.dto.LoginReqDto;
import com.cos.project.domain.user.dto.UpdateReqDto;

public class UserService {

	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public int 회원가입(JoinReqDto dto) {
		return userDao.save(dto);
	}
	
	public User 로그인 (LoginReqDto dto) {
		return userDao.findByUsernameAndPassword(dto);
	}
	
	public int 회원정보수정 (UpdateReqDto dto) {
		return userDao.update(dto);
	}
	
	public int 유저네임중복체크(String username) {
		return userDao.findByUsername(username);
	}
	
	public int 회원탈퇴(int id) {
		return userDao.delete(id);
	}
	
	public User 세션갱신(int id) {
		return userDao.findById(id);
	}
	
	public int 패스워드확인(int id, String password) {
		String principalPw = userDao.pwFindById(id);
		if(password.equals(principalPw)) {
			return 1;
		} else {
			return -1;
		}
	}
}
