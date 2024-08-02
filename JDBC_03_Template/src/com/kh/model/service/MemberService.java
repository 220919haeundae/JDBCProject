package com.kh.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {
	/*
	 * [1] Connection 객체 생성
	 * 		- jdbc driver 등록
	 * 		- Connection 객체 생성
	 * [2] DML문 실행 시 트랜잭션 처리
	 * 		- commit
	 * 		- rollback
	 * [3] Connection 객체 반납
	 * 		- close
	 */
	public int insertMember(Member m) {
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) DAO에게 전달받은 데이터(Member m)와 Connection 객체를 전달하여
		// DB처리 결과를 받기
		int result = new MemberDao().insertMember(conn, m);
		
		// 3) DML(INSERT) 실행 후 트랜잭션 처리
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		return result;
	}
	
	
	public ArrayList<Member> selectList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> list = new MemberDao().selectList(conn);
		
		if(!list.isEmpty()) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int modifyInfo(int memberNo, String target, String info) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().modifyInfo(conn, memberNo, target, info);
		JDBCTemplate.close(conn);;
		return result;
	}
	
	public int deleteMember(String id) {
		int result = 0;
		
		Connection conn = JDBCTemplate.getConnection();
		
		result = new MemberDao().deleteMember(conn, id);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
}
