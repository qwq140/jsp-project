package com.cos.project.domain.book;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.project.config.DBConn;
import com.cos.project.domain.book.dto.SaveReqDto;

public class BookDao {
	
	//항공편 예약하기
	public int save(SaveReqDto dto) {
		String sql = "INSERT INTO book(userId, personnel, depAirportNm, arrAirportNm, depPlandTime, arrPlandTime, vihicleId, grade, charge) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = DBConn.getConnection();
		PreparedStatement pstmt = null;
		System.out.println("bookDao.save");
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
