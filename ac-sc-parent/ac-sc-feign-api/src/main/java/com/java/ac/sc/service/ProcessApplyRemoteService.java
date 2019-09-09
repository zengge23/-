package com.java.ac.sc.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.ac.sc.entities.Cert;
import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.MemberCert;
import com.java.ac.sc.entities.ResultEntity;
import com.java.ac.sc.entities.Ticket;

@FeignClient("PortalProvider")
public interface ProcessApplyRemoteService {
	
	@RequestMapping("/get/ticke/bymemberid/remote/{memberId}")
	ResultEntity<Ticket> getTickeByMemberIdRemote(@PathVariable("memberId") Integer memberId);

	@RequestMapping("save/ticket/remote")
	ResultEntity<String> saveTicketRemote(@RequestBody Ticket ticket);

	@RequestMapping("update/member/auth/status/remote/{memberId}/{authStatus}")
	ResultEntity<String> updateMemberAuthStatusRemote(@PathVariable("memberId")Integer memberId, @PathVariable("authStatus")String authStatus);

	@RequestMapping("update/pstep/byMemberId/remote/{memberId}/{pstep}")
	ResultEntity<String> updatePstepByMemberIdRemote(@PathVariable("memberId")Integer memberId, @PathVariable("pstep")String pstep);

	@RequestMapping("update/member/acctType/remote/{memberId}/{accttype}")
	ResultEntity<String> updateMemberAcctTypeRemote(@PathVariable("memberId")Integer memberId, @PathVariable("accttype")String accttype);

	@RequestMapping("update/member/basicinfo/remote")
	ResultEntity<String> updateMemberBasicinfoRemote(@RequestBody Member member);

	@RequestMapping("get/cert/list/byAcctype/remote/{accType}")
	ResultEntity<List<Cert>> getCertListByAcctypeRemote(@PathVariable("accType") Byte accType);

	@RequestMapping("save/member/certList/remote")
	ResultEntity<String> saveMemberCertListRemote(@RequestBody List<MemberCert> memberParamList);

	@RequestMapping("update/member/emailAddr/remote")
	ResultEntity<String> updateMemberEmailAddrRemote(@RequestParam HashMap<String, String> hashMap);
	
	@RequestMapping("update/pstep/and/code/byMemberId/remote/{memberId}/{pstep}/{checkCode}")
	ResultEntity<String> updatePstepAndCodeByMemberIdRemote(@PathVariable("memberId") Integer memberId, @PathVariable("pstep") String pstep, @PathVariable("checkCode") String checkCode);

	@RequestMapping("get/checkCode/byMemberId/remote/{memberId}")
	ResultEntity<String> getCheckCodeByMemberId(@PathVariable("memberId")Integer memberId);

}
