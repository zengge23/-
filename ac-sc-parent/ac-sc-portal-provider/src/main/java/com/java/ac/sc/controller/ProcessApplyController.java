package com.java.ac.sc.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.ac.sc.entities.Cert;
import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.MemberCert;
import com.java.ac.sc.entities.ResultEntity;
import com.java.ac.sc.entities.Ticket;
import com.java.ac.sc.service.MemberService;
import com.java.ac.sc.service.TicketService;

@RestController
public class ProcessApplyController {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("get/checkCode/byMemberId/remote/{memberId}")
	ResultEntity<String> getCheckCodeByMemberId(@PathVariable("memberId")Integer memberId){
		Ticket ticket = ticketService.getByMemberId(memberId);
		String checkCode = ticket.getAuthcode();
		return new ResultEntity<String>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,checkCode);
	}
	
	@RequestMapping("update/pstep/and/code/byMemberId/remote/{memberId}/{pstep}/{checkCode}")
	public ResultEntity<String> updatePstepAndCodeByMemberIdRemote(@PathVariable("memberId") Integer memberId, @PathVariable("pstep") String pstep, @PathVariable("checkCode") String checkCode){
		ticketService.updatePstepAndCodeByMemberId(memberId,pstep,checkCode);
		return new ResultEntity<String>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,ResultEntity.NO_DATA);
	}

	
	@RequestMapping("update/member/emailAddr/remote")
	public ResultEntity<String> updateMemberEmailAddrRemote(@RequestParam HashMap<String, String> hashMap){
		String memberId = hashMap.get("memberId");
		String emailAddr = hashMap.get("emailAddr");
		memberService.updateEmailAddrByMemberId(memberId,emailAddr);
		return new ResultEntity<String>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,ResultEntity.NO_DATA);
	}
	
	@RequestMapping("/get/ticke/bymemberid/remote/{memberId}")
	public ResultEntity<Ticket> getTickeByMemberIdRemote(@PathVariable("memberId") Integer memberId){
		ResultEntity<Ticket> resultEntity = new ResultEntity<>();
		
		try {
			Ticket ticket = ticketService.getByMemberId(memberId);
			resultEntity.setResult(ResultEntity.SUCCESS);
			resultEntity.setDataT(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}
		
		return resultEntity;
	}

	@RequestMapping("save/ticket/remote")
	public ResultEntity<String> saveTicketRemote(@RequestBody Ticket ticket){
		ticketService.save(ticket);
		return new ResultEntity<String>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,ResultEntity.NO_DATA);
	}
	
	@RequestMapping("update/member/auth/status/remote/{memberId}/{authStatus}")
	public ResultEntity<String> updateMemberAuthStatusRemote(@PathVariable("memberId")Integer memberId, @PathVariable("authStatus")String authStatus){
		memberService.updateMemberAuthStatus(memberId,authStatus);
		return new ResultEntity<String>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,ResultEntity.NO_DATA);
	}
	
	@RequestMapping("update/pstep/byMemberId/remote/{memberId}/{pstep}")
	public ResultEntity<String> updatePstepByMemberIdRemote(@PathVariable("memberId")Integer memberId, @PathVariable("pstep")String pstep){
		ticketService.updatePstepByMemberId(memberId,pstep);
		return new ResultEntity<String>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,ResultEntity.NO_DATA);
	}
	
	@RequestMapping("update/member/acctType/remote/{memberId}/{accttype}")
	ResultEntity<String> updateMemberAcctTypeRemote(@PathVariable("memberId")Integer memberId, @PathVariable("accttype")String accttype){
		memberService.updateAccttypeByMemberId(memberId,accttype);
		return new ResultEntity<String>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,ResultEntity.NO_DATA);
	}
	
	@RequestMapping("update/member/basicinfo/remote")
	ResultEntity<String> updateMemberBasicinfoRemote(@RequestBody Member member){
		memberService.updateMemberBasicinfo(member);
		return new ResultEntity<String>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,ResultEntity.NO_DATA);
	}
	
	@RequestMapping("get/cert/list/byAcctype/remote/{accType}")
	ResultEntity<List<Cert>> getCertListByAcctypeRemote(@PathVariable("accType") Byte accType){
		ResultEntity<List<Cert>> resultEntity = new ResultEntity<>();
		List<Cert> list = memberService.getCertListByAcctypeRemote(accType);
		resultEntity.setDataT(list);
		return resultEntity;
	}
	
//	@RequestMapping("save/member/certList/remote")
//	ResultEntity<List<Member>> saveMemberCertListRemote(){
//		ResultEntity<List<Member>> resultEntity = new ResultEntity<>();
//			List<Member> list = memberService.getAll();
//			resultEntity.setDataT(list);
//			resultEntity.setResult(ResultEntity.SUCCESS);
//		return resultEntity;
//	}
//	
	@RequestMapping("save/member/certList/remote")
	ResultEntity<String> saveMemberCertListRemote(@RequestBody List<MemberCert> memberParamList){
		ResultEntity<String> resultEntity = new ResultEntity<>();
		try {
			memberService.saveMemberCertList(memberParamList);
			resultEntity.setResult(ResultEntity.SUCCESS);
		} catch (Exception e) {
			resultEntity.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return resultEntity;
	}
}
