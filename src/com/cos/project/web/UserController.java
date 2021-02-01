package com.cos.project.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.project.domain.CommonRespDto;
import com.cos.project.domain.user.User;
import com.cos.project.domain.user.dto.JoinReqDto;
import com.cos.project.domain.user.dto.LoginReqDto;
import com.cos.project.domain.user.dto.UpdateReqDto;
import com.cos.project.service.UserService;
import com.cos.project.util.Script;
import com.google.gson.Gson;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

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
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			JoinReqDto dto = new JoinReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPhone(phone);
			
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
		} else if(cmd.equals("infoForm")) {
			RequestDispatcher dis = request.getRequestDispatcher("user/infoForm.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("updateForm")) {
			RequestDispatcher dis = request.getRequestDispatcher("user/updateForm.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			
			UpdateReqDto dto = new UpdateReqDto();
			dto.setId(id);
			dto.setPassword(password);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPhone(phone);
			
			int result = userService.회원정보수정(dto);
			if(result == 1) {
				HttpSession session = request.getSession();
				session.invalidate();
				RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "회원정보수정 실패");
			}
		} else if(cmd.equals("usernameCheck")) {
			String username = request.getParameter("username");
			int result = userService.유저네임중복체크(username);
			
			CommonRespDto<Object> commonRespDto = new CommonRespDto<>();
			if(result == 1) {
				commonRespDto.setStatusCode(1);
			} else {
				commonRespDto.setStatusCode(-1);
			}
			
			Gson gson = new Gson();
			String responseData = gson.toJson(commonRespDto);
			System.out.println("responseData : " +responseData);
			Script.responseData(response, responseData);
		} else if(cmd.equals("deleteForm")) {
			RequestDispatcher dis = request.getRequestDispatcher("user/deleteForm.jsp");
			dis.forward(request, response);
		} else if(cmd.equals("delete")) {
			HttpSession session = request.getSession();
			User principal = (User)session.getAttribute("principal");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if (principal.getUsername().equals(username)) {
				int pwResult = userService.패스워드확인(principal.getId(), password);
				if(pwResult == 1) {
					int result = userService.회원탈퇴(principal.getId());
					if(result==1) {
						session.invalidate();
						response.sendRedirect("index.jsp");
					} else {
						Script.back(response, "삭제 실패");
					}
				} else {
					Script.back(response, "username또는 password가 일치하지 않습니다.");
				}
			} else {
				Script.back(response, "username또는 password가 일치하지 않습니다.");
			}
		}
	}

}
