package com.java.ac.sc.service;

import java.util.List;

import com.java.ac.sc.entities.Cert;
import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.MemberCert;

public interface MemberService {
	
    int removeByPrimaryKey(Integer memberId);

    int save(Member record);

    Member getByPrimaryKey(Integer memberId);

    List<Member> getAll();

    int updateByPrimaryKey(Member record);

	int getByloginAcc(String loginAcc);

	Member getByLoginAccAndLoginPwd(Member member);

	int updateMemberAuthStatus(Integer memberId, String authStatus);

	int updateAccttypeByMemberId(Integer memberId, String accttype);

	int updateMemberBasicinfo(Member member);

	List<Cert> getCertListByAcctypeRemote(Byte accType);

	int saveMemberCertList(List<MemberCert> memberParamList);

	int updateEmailAddrByMemberId(String memberId, String emailAddr);

}
