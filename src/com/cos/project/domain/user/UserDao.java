package com.cos.project.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.project.config.DBConn;
import com.cos.project.domain.user.dto.JoinReqDto;
import com.cos.project.domain.user.dto.LoginReqDto;
import com.cos.project.domain.user.dto.UpdateReqDto;

public class UserDao {
	
	// 유저네임 중복확인
	public int findByUsername(String username) {
			String sql = "SELECT * FROM user WHERE username = ?";
			Connection conn = DBConn.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, username);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					return 1; // 있음
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBConn.close(conn, pstmt, rs);
			}
			return -1; // 없음
		}
	
	// 회원정보수정
	public int update(UpdateReqDto dto) {
		String sql = "UPDATE user SET password = ?, name = ? ,email = ?, phone = ? WHERE id = ?";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPhone());
			pstmt.setInt(5, dto.getId());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	
	// 회원가입
	public int save(JoinReqDto dto) {
		String sql = "INSERT INTO user(username, password, name, email, phone, role, createDate) VALUES(?, ?, ?, ?, ?, 'USER', now())";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	
	// 로그인
	public User findByUsernameAndPassword(LoginReqDto dto) {
		String sql = "SELECT id, username, name, email, phone, role FROM user WHERE username = ? AND password = ?";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.role(rs.getString("role"))
						.build();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
}
