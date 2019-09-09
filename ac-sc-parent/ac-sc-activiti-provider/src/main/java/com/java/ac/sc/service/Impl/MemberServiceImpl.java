package com.java.ac.sc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.ac.sc.entities.Member;
import com.java.ac.sc.mapper.MemberMapper;
import com.java.ac.sc.service.MemberService;

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public Member getMemberListByprocessInstanceId(String processInstanceId) {
		return memberMapper.selectMemberListByprocessInstanceId(processInstanceId);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int updateMemberAuthStatusByPiid(String processInstanceId, String authStatus) {
		
			return memberMapper.updateMemberAuthStatusByPiid(processInstanceId,authStatus);
	}

}
