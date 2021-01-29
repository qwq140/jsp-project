package com.cos.project.domain.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.project.config.DBConn;
import com.cos.project.domain.book.dto.SaveReqDto;
import com.cos.project.domain.user.User;

public class BookDao {
	
	public List<Book> findById(int userId){
		String sql = "SELECT id, userId, personnel, depAirportNm, arrAirportNm, depPlandTime, arrPlandTime, vihicleId, grade, charge FROM book WHERE userId=?"; 
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();
			List<Book> bookList = new ArrayList<>();
			while(rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setUserId(rs.getInt("userId"));
				book.setPersonnel(rs.getInt("personnel"));
				book.setDepAirportNm(rs.getString("depAirportNm"));
				book.setArrAirportNm(rs.getString("arrAirportNm"));
				book.setDepPlandTime(rs.getString("depPlandTime"));
				book.setArrPlandTime(rs.getString("arrPlandTime"));
				book.setVihicleId(rs.getString("vihicleId"));
				book.setGrade(rs.getString("grade"));
				book.setCharge(rs.getInt("charge"));
				
				bookList.add(book);
			}
			return bookList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
	
	//항공편 예약하기
	public int save(SaveReqDto dto) {
		String sql = "INSERT INTO book(userId, personnel, depAirportNm, arrAirportNm, depPlandTime, arrPlandTime, vihicleId, grade, charge) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getPersonnel());
			pstmt.setString(3, dto.getDepAirportNm());
			pstmt.setString(4, dto.getArrAirportNm());
			pstmt.setString(5, dto.getDepPlandTime());
			pstmt.setString(6, dto.getArrPlandTime());
			pstmt.setString(7, dto.getVihicleId());
			pstmt.setString(8, dto.getGrade());
			pstmt.setInt(9, dto.getCharge());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
}
