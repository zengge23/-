package com.java.ac.sc.service;

import com.java.ac.sc.entities.Member;

public interface MemberService {

    Member getMemberListByprocessInstanceId(String processInstanceId);

	int updateMemberAuthStatusByPiid(String processInstanceId, String authStatus);

}