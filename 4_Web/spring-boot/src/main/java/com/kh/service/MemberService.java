package com.kh.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;


@Service
public class MemberService {

	@Autowired
	private MemberDAO dao;
	
	public void register(Member vo) throws SQLException {
		dao.registerMember(vo);
	}
	
	public Member login(Member vo) throws SQLException {
		return dao.loginMember(vo.getId(), vo.getPassword());
	}
	
	public Member search(String id) throws SQLException {
		return dao.searchMember(id);
	}
	
	public List<Member> allMember() throws SQLException {
		return dao.allSearchMember();
	}

}
