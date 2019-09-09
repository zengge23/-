package com.java.ac.sc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.ac.sc.entities.Cert;
import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.MemberCert;
import com.java.ac.sc.mapper.CertMapper;
import com.java.ac.sc.mapper.MemberCertMapper;
import com.java.ac.sc.mapper.MemberMapper;
import com.java.ac.sc.service.MemberService;
import com.java.ac.sc.utils.StringUtils;

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	CertMapper certMapper;
	
	@Autowired
	MemberCertMapper memberCertMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int removeByPrimaryKey(Integer memberId) {
		return memberMapper.deleteByPrimaryKey(memberId);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int save(Member record) {
		return memberMapper.insert(record);
	}

	@Override
	public Member getByPrimaryKey(Integer memberId) {
		return memberMapper.selectByPrimaryKey(memberId);
	}

	@Override
	public List<Member> getAll() {
		return memberMapper.selectAll();
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int updateByPrimaryKey(Member record) {
		return memberMapper.updateByPrimaryKey(record);
	}

	@Override
	public int getByloginAcc(String loginAcc) {
		return memberMapper.selectByLoginAcc(loginAcc);
	}

	@Override
	public Member getByLoginAccAndLoginPwd(Member member) {
		String loginPwd = StringUtils.md5(member.getLoginPwd());
		member.setLoginPwd(loginPwd);
		return memberMapper.selectByLoginAccAndLoginPwd(member);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int updateMemberAuthStatus(Integer memberId, String authStatus) {
		return memberMapper.updateMemberAuthStatus(memberId,authStatus);
	}

	@Override
	@Transactional(readOnly = false,noRollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int updateAccttypeByMemberId(Integer memberId, String accttype) {
		return memberMapper.updateAccttypeByMemberId(memberId,accttype);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int updateMemberBasicinfo(Member member) {
		return memberMapper.updateMemberBasicinfo(member);
	}

	@Override
	public List<Cert> getCertListByAcctypeRemote(Byte accType) {
		return certMapper.getCertListByAcctype(accType);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int saveMemberCertList(List<MemberCert> memberParamList) {
		return memberCertMapper.insertList(memberParamList);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int updateEmailAddrByMemberId(String memberId, String emailAddr) {
		return memberMapper.updateEmailAddrByMemberId(memberId,emailAddr);
	}

}
