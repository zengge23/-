package com.java.ac.sc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.java.ac.sc.entities.Cert;
import com.java.ac.sc.entities.CertParam;
import com.java.ac.sc.entities.CertParamWrapper;
import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.MemberCert;
import com.java.ac.sc.entities.ResultEntity;
import com.java.ac.sc.entities.Ticket;
import com.java.ac.sc.service.ProcessApplyRemoteService;
import com.java.ac.sc.service.ProcessRemoteService;
import com.java.ac.sc.utils.StringUtils;

@Controller
public class ProcessController {
	
	@Autowired
	ProcessApplyRemoteService processApplyRemoteService;
	
	@Autowired
	ProcessRemoteService processRemoteService;
	
	@Autowired
	StorageClient storageClient;
	
	@RequestMapping("/consumer/process/againcheck/code")
	public String consumerProcessAgaincheckCode(@RequestParam String newEmail,HttpSession session,Model model) {
		Member member = (Member) session.getAttribute("LOGIN_MEMBER");
		if (member == null) {
			model.addAttribute("MESSAGE","请登录后继操作");
			return "/member/login";
		}
		String checkCode = StringUtils.checkCode();
		String loginAcc = member.getLoginAcc();
		boolean bool = false;
		ResultEntity<String> resultEntity = processRemoteService.completeMemberTask3Remote(loginAcc,newEmail,bool,checkCode);
		String result = resultEntity.getResult();
		if (result.equals(ResultEntity.SUCCESS)) {
			Integer memberId = member.getMemberId();
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("memberId",memberId + "");
			hashMap.put("emailAddr",newEmail);
			processApplyRemoteService.updateMemberEmailAddrRemote(hashMap);
			
			String pstep = "codecheck";
			processApplyRemoteService.updatePstepAndCodeByMemberIdRemote(memberId,pstep,checkCode);
			return "/process/codecheck";
		}else {
			model.addAttribute("MESSAGE",resultEntity.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("/consumer/process/checke/code")
	public String consumerProcessCheckeCode(@RequestParam String checkCode,HttpSession session,Model model){
		Member member = (Member) session.getAttribute("LOGIN_MEMBER");
		if (member == null) {
			model.addAttribute("MESSAGE","请登录后继操作");
			return "/member/login";
		}
		Integer memberId = member.getMemberId();
		boolean stringCheckForm = StringUtils.stringCheck(checkCode);
		String checkCodeDB = processApplyRemoteService.getCheckCodeByMemberId(memberId).getDataT();
		boolean stringCheck = StringUtils.stringCheck(checkCodeDB);
		if (!stringCheckForm) {
			new RuntimeException("数据库验证码获取失败!");
		}
		if (!stringCheck) {
			model.addAttribute("MESSAGE","验证码输入有误,请重新输入!");
			return "/process/codecheck";
		}
		if (checkCodeDB.equals(checkCode)) {
			String loginAcc = member.getLoginAcc();
			ResultEntity<String> resultEntity = processRemoteService.completeMemberTask2Remote(loginAcc,true);
			String result = resultEntity.getResult();
			if (result.equals(ResultEntity.SUCCESS)){
				String authStatus = "2";
				processApplyRemoteService.updateMemberAuthStatusRemote(memberId,authStatus);
				member.setAuthStatus(new Byte(authStatus));
				return "/member/member";
			}else {
				model.addAttribute("MESSAGE",resultEntity.getMessage());
				return "error";
			}
		}else {
			model.addAttribute("MESSAGE","验证码输入有误,请重新输入!");
			return "/process/codecheck";
		}
	}
	
	@RequestMapping("/consumer/process/email/address")
	public String consumerProcessEmailAddress(@RequestParam String emailAddress,HttpSession session,Model model) {
		Member member = (Member) session.getAttribute("LOGIN_MEMBER");
		if (member == null) {
			model.addAttribute("MESSAGE","请登录后继操作");
			return "/member/login";
		}
		String checkCode = StringUtils.checkCode();
		String loginAcc = member.getLoginAcc();
		boolean bool = true;
		ResultEntity<String> resultEntity = processRemoteService.completeMemberTask3Remote(loginAcc,emailAddress,bool,checkCode);
		String result = resultEntity.getResult();
		if (result.equals(ResultEntity.SUCCESS)) {
			Integer memberId = member.getMemberId();
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("memberId",memberId + "");
			hashMap.put("emailAddr",emailAddress);
			processApplyRemoteService.updateMemberEmailAddrRemote(hashMap);
			member.setEmailAddr(emailAddress);
			String pstep = "codecheck";
			processApplyRemoteService.updatePstepAndCodeByMemberIdRemote(memberId,pstep,checkCode);
			return "/process/codecheck";
		}else {
			model.addAttribute("MESSAGE",resultEntity.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("/consumer/process/uploadfile")
	public String consumerProcessUploadfile(CertParamWrapper wrapper,HttpSession session,Model model) throws IOException, MyException {
		Member member = (Member) session.getAttribute("LOGIN_MEMBER");
		if (member == null) {
			model.addAttribute("MESSAGE","请登录后继操作");
			return "/member/login";
		}
		Integer memberId = member.getMemberId();
		List<CertParam> certParamList = wrapper.getCertParamList();
		List<MemberCert> memberParamList = new ArrayList<>();
		for (CertParam certParam : certParamList) {
			Integer certId = certParam.getCertId();
			MultipartFile certFile = certParam.getCertFile();
			
			byte[] bytes = certFile.getBytes();
			String originalFilename = certFile.getOriginalFilename();
			String file_ext_name = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			String[] resultArray = storageClient.upload_file(bytes,file_ext_name,null);
			
			String groupName = resultArray[0];
			String remoteFileName = resultArray[1];
			String iconPath = groupName + "/" + remoteFileName;
			System.err.println(iconPath);
			MemberCert memberCert = new MemberCert(null,memberId,certId,iconPath);
			memberParamList.add(memberCert);
		}
		String loginAcc = member.getLoginAcc();
		ResultEntity<String> resultEntity = processRemoteService.completeMemberTask2Remote(loginAcc,true);
		String result = resultEntity.getResult();
		if (result.equals(ResultEntity.SUCCESS)) {
			processApplyRemoteService.saveMemberCertListRemote(memberParamList);
			String pstep = "emailaddress";
			processApplyRemoteService.updatePstepByMemberIdRemote(memberId,pstep);
			return "/process/emailaddress";
		}else {
			model.addAttribute("MESSAGE",resultEntity.getMessage());
			return "error";
		}
		
	}
	
	@RequestMapping("/consumer/process/basicinfo")
	public String consumerProcessBasicinfo(@RequestParam("realName")String realName,@RequestParam("cardNum")String cardNum,@RequestParam("phoneNum")String phoneNum,HttpSession session,Model model) {
		Member member = (Member) session.getAttribute("LOGIN_MEMBER");
		if (member == null) {
			model.addAttribute("MESSAGE","请登录后继操作");
			return "/member/login";
		}
		String loginAcc = member.getLoginAcc();
		ResultEntity<String> resultEntity = processRemoteService.completeMemberTask2Remote(loginAcc,true);
		String result = resultEntity.getResult();
		if (result.equals(ResultEntity.SUCCESS)) {
			Integer memberId = member.getMemberId();
			String pstep = "uploadfile";
			processApplyRemoteService.updatePstepByMemberIdRemote(memberId,pstep);
			
			member.setRealName(realName);
			member.setCardNum(cardNum);
			member.setPhoneNum(phoneNum);
			processApplyRemoteService.updateMemberBasicinfoRemote(member);
			
			Byte accType = member.getAccType();
			ResultEntity<List<Cert>> certListByAcctypeRemote = processApplyRemoteService.getCertListByAcctypeRemote(accType);
			List<Cert> list = certListByAcctypeRemote.getDataT();
			System.err.println(list);
			model.addAttribute("certList",list);
			return "/process/uploadfile";
		}else {
			model.addAttribute("MESSAGE",resultEntity.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("/consumer/process/accttype/{accttype}")
	public String consumerProcessAccttype(@PathVariable("accttype")String accttype,HttpSession session,Model model) {
		Member member = (Member) session.getAttribute("LOGIN_MEMBER");
		if (member == null) {
			model.addAttribute("MESSAGE","请重新登录后继续操作");
			return "/member/login";
		}
		String loginAcc = member.getLoginAcc();
		ResultEntity<String> resultEntity = processRemoteService.completeMemberTaskRemote(loginAcc);
		String result = resultEntity.getResult();
		if (result.equals(ResultEntity.SUCCESS)) {
			Integer memberId = member.getMemberId();
			String pstep = "basicinfo";
			processApplyRemoteService.updatePstepByMemberIdRemote(memberId,pstep);
			processApplyRemoteService.updateMemberAcctTypeRemote(memberId,accttype);
			member.setAccType(new Byte(accttype));
		}else {
			model.addAttribute("MESSAGE",resultEntity.getMessage());
			return "error";
		}
		return "process/basicinfo";
	}
	
	@RequestMapping("/consumer/process/apply")
	public String consumerProcessApply(HttpSession session,Model model) {
		
		Member member = (Member) session.getAttribute("LOGIN_MEMBER");
		if (member == null) {
			model.addAttribute("MESSAGE","登录超时,请重新登录");
			return "/member/login";
		}
		Integer memberId = member.getMemberId();
		ResultEntity<Ticket> getTickeByMemberId = processApplyRemoteService.getTickeByMemberIdRemote(memberId);
		Ticket ticket = getTickeByMemberId.getDataT();
		String loginAcc = member.getLoginAcc();
		String viemName = null;
		if (ticket == null) {
			//启动流程实例
			ResultEntity<String> startRealNameAuthProcessInstance = processRemoteService.startRealNameAuthProcessInstance(loginAcc);
			String result = startRealNameAuthProcessInstance.getResult();
			if (ResultEntity.SUCCESS.equals(result)) {
				ticket = new Ticket(null,memberId,startRealNameAuthProcessInstance.getDataT(),"0",null,"accttype");
				//在t_ticket表中保存数据
				processApplyRemoteService.saveTicketRemote(ticket);
				//将t_member表中的auth_status改成1
				String authStatus = "1";
				processApplyRemoteService.updateMemberAuthStatusRemote(memberId,authStatus);
				member.setAuthStatus(new Byte(authStatus));
			}else {
				model.addAttribute("MESSAGE",startRealNameAuthProcessInstance.getMessage());
				return "error";
			}
			viemName = "process/accttype";
		}else {
			String pstep = ticket.getPstep();
			viemName = "process/" + pstep;
		}
		return viemName;
	}

}
