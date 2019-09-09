package com.java.ac.sc.mapper;

import com.java.ac.sc.entities.Member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(Member record);

    Member selectByPrimaryKey(Integer memberId);

    List<Member> selectAll();

    int updateByPrimaryKey(Member record);

	int selectByLoginAcc(String loginAcc);
	
	Member selectByLoginAccAndLoginPwd(Member member);
	
	int updateMemberAuthStatus(@Param("memberId") Integer memberId,@Param("authStatus")String authStatus);

	int updateAccttypeByMemberId(@Param("memberId")Integer memberId, @Param("accttype")String accttype);

	int updateMemberBasicinfo(Member member);

	int updateEmailAddrByMemberId(@Param("memberId")String memberId, @Param("emailAddr")String emailAddr);

}