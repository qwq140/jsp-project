package com.cos.project.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.project.config.DBConn;
import com.cos.project.domain.user.dto.JoinReqDto;
import com.cos.project.domain.user.dto.LoginReqDto;

public class UserDao {
	
	// 회원가입
	public int save(JoinReqDto dto) {
		String sql = "INSERT INTO user(username, password, email, address, userRole, createDate) VALUES(?, ?, ?, ?, 'USER', now())";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	
	public User findByUsernameAndPassword(LoginReqDto dto) {
		String sql = "SELECT id, username, email, address FROM user WHERE username = ? AND password = ?";
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
						.email(rs.getString("email"))
						.address(rs.getString("address"))
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
