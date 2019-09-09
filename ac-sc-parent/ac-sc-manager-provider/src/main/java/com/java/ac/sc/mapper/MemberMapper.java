package com.java.ac.sc.mapper;

import java.util.List;
import java.util.Map;

import com.java.ac.sc.entities.Member;

public interface MemberMapper {

	Member selectMemberByMemberId(Integer memberId);

	List<Map<String, String>> selectMemberCertDetail(Integer memberId);
	
}