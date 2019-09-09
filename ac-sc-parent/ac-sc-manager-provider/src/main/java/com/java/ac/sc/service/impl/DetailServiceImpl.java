package com.java.ac.sc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.ac.sc.entities.Member;
import com.java.ac.sc.mapper.MemberMapper;
import com.java.ac.sc.service.DetailService;

@Service
@Transactional(readOnly=true)
public class DetailServiceImpl implements DetailService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public Member getMemberByMemberId(Integer memberId) {
		return memberMapper.selectMemberByMemberId(memberId);
	}

	@Override
	public List<Map<String, String>> getMemberCertDetail(Integer memberId) {
		return memberMapper.selectMemberCertDetail(memberId);
	}

}
