package com.java.ac.sc.mapper;

import org.apache.ibatis.annotations.Param;

import com.java.ac.sc.entities.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(Member record);

	Member selectMemberListByprocessInstanceId(String processInstanceId);

	int updateMemberAuthStatusByPiid(@Param("processInstanceId")String processInstanceId, @Param("authStatus")String authStatus);

}