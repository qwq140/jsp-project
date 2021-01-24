package com.cos.project.web;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.project.domain.user.User;
import com.cos.project.domain.user.dto.JoinReqDto;
import com.cos.project.domain.user.dto.LoginReqDto;
import com.cos.project.service.UserService;
import com.cos.project.util.Script;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		if(cmd.equals("joinForm")) {
			RequestDispatcher dis = request.getRequestDispatcher("user/joinForm.jsp");
			dis.forward(request, response);
		} else if (cmd.equals("join")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			
			JoinReqDto dto = new JoinReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setEmail(email);
			dto.setAddress(address);
			
			System.out.println("JoinReqDto : "+ dto);
			
			int result = userService.회원가입(dto);
			
			if(result == 1) {
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "회원가입 실패");
			}
		} else if(cmd.equals("loginForm")) {
			RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			LoginReqDto dto = new LoginReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			
			User userEntity = userService.로그인(dto);
			System.out.println(dto);
			if(userEntity != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity);
				response.sendRedirect("index.jsp");
			} else {
				Script.back(response, "로그인실패");
			}
			
			
		} else if(cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
	}

}
