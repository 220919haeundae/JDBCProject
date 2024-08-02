package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.Member;

public class MemberDao {
	
	public int insertMember(Connection conn, Member m) {
		int rows = 0;
		String sql = "INSERT INTO MEMBER VALUES (SEQ_USERNO.NEXTVAL, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPw());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getPhone());
			pstmt.setString(9, m.getHobby());
			
			rows = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return rows;
	}
	
	public ArrayList<Member> selectList(Connection conn) {
		ArrayList<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM MEMBER";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(
						new Member(
							  rset.getInt("USERNO")
							, rset.getString("USERID")
							, rset.getString("USERPW")
							, rset.getString("USERNAME")
							, rset.getString("GENDER")
							, rset.getInt("AGE")
							, rset.getString("EMAIL") 
							, rset.getString("ADDRESS")
							, rset.getString("PHONE")
							, rset.getString("HOBBY")
							, rset.getDate("ENROLLDATE")
						)
					);
			
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}
	
	public int modifyInfo(Connection conn,int memberNo, String target, String info) {
		int result = 0;
		String sql = "UPDATE MEMBER SET " + target + " = ? WHERE USERNO = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	
	public int deleteMember(Connection conn,String id) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	

}
