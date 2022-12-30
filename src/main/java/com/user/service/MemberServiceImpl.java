package com.user.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.user.mapper.MemberMapper;
import com.user.model.MemberVO;
import com.user.model.NotUserException;
import com.user.model.PagingVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberMapper MemberMapper;

	@Override
	public int createMember(MemberVO Member) {
		return MemberMapper.createMember(Member);
	}

	@Override
	public int getMemberCount(PagingVO pvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> listUser(PagingVO pvo) {
		return MemberMapper.listUser(pvo);
	}

	@Override
	public boolean EmailCheck(String email) {
		Integer n = MemberMapper.emailCheck(email);
		if (n == null) {
			return true;
		}
		return false;
	}

	@Override
	public int deleteMember(Integer midx) {
		return this.MemberMapper.deleteUser(midx);
	}

	@Override
	public int updateMember(MemberVO member) {
		
		return this.MemberMapper.updateUser(member);
	}

	@Override
	public MemberVO getMember(Integer midx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO findUser(MemberVO findUser) throws NotUserException {
		MemberVO user = MemberMapper.findUser(findUser);
		if (user == null) {
			throw new NotUserException("존재하지 않는 아이디에요");
		}
		return user;
	}

	@Override
	public MemberVO loginCheck(String email, String pwd) throws NotUserException {
		MemberVO tmpVo = new MemberVO();
		tmpVo.setEmail(email);
		
		MemberVO member = this.findUser(tmpVo);
		if (member == null) {
			throw new NotUserException("존재하지 않는 아이디에요");
		}
		if (!member.getPwd().equals(pwd)) {
			throw new NotUserException("비밀번호가 일치하지 않아요");
		}
		return member;
	}
}