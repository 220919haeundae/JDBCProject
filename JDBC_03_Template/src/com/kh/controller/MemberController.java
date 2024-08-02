package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Cotroller : View를 통해 사용자가 요청한 기능에 대해 처리하는 역할
//				요청받은 메소드에서 전달된 데이터를 가공처리한 후 DAO로 전달하여 호출
// 				DAO로부터 반환받은 결과에 따라 성공인지 실패인지를 판단한 후 응답
public class MemberController {

	
	/**
	 * 회원 추가 요청을 처리할 메소드
	 * @param userId
	 * @param userPw
	 * @param name
	 * @param gender
	 */
	public void insertMember(String userId, String userPw, String name, String gender) {
		// view로부터 전달받은 값들을 dao에게 바로 전달하지 않고
		// 어딘가(Member)에 담아서 전달
		
		// * 기본생성자 생성 후 setter 메소드 하나하나 저장
		// * 매개변수 생성자를 사용하여 객체 생성
		Member m = new Member(userId, userPw, name, gender);
		
		int result = new MemberService().insertMember(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 추가 성공했습니다.");
		} else {
			new MemberMenu().displaySuccess("회원 추가 실패했습니다.");
		}
	}
	/**
	 * 전체 회원 목록 조회 메소드
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberService().selectList();
		
		// 조회된 결과에 따라 사용자에게 결과 표시
		if(list.isEmpty()) {
			// 조회결과가 없다면 (=> 리스트가 비어있다면)
			new MemberMenu().displayNoData("전체 조회 결과가 없음!");
		} else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	public void searchID(String ID) {
		Member m = new MemberDao().searchID(ID);
		
		// 조회된 결과에 따라 사용자에게 결과 표시
		if(m == null) {
			// 검색결과가 없는 경우
			new MemberMenu().displayNoData(ID + "에 해당하는 결과가 없음!");
		} else {
			// 검색결과가 있는 경우
			new MemberMenu().displayMember(m);
		}
	}
	
	public int withdrawal(String id) {
		int answer = new MemberService().deleteMember(id);
		return answer;
	}
	
	public String modifyInfo(int memberNo, String target, String info) {
		if(new MemberService().modifyInfo(memberNo, target, info) > 0 ) {
			return "정보 수정에 성공했습니다.";
		} else {
			return "정보 수정에 실패했습니다..";
		}
	}
	
	public void searchByName(String keyword) {
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if (list.isEmpty()) {
			new MemberMenu().displayNoData("해당 이름에 대한 회원정보가 없습니다.");
		} else {
			new MemberMenu().displayMemberList(list);
		}
		
	}
	
}