package com.java.ac.sc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.ResultEntity;
import com.java.ac.sc.service.MemberRemoteService;

@Controller
public class MemberController {

	@Autowired
	MemberRemoteService memberRemoteService;
	
	@RequestMapping("/member/register")
	public String toRegisterPage() {
		return "/member/register";
	}
	
	@RequestMapping("/member/toLoginPage")
	public String toLoginPage() {
		return "/member/login";
	}
	
	@RequestMapping("/member/doRegister")
	public String doRegister(Member member,Model model){
		ResultEntity<String> doRegisterRemote = memberRemoteService.doRegisterRemote(member);
		String result = doRegisterRemote.getResult();
		if (result.equals(ResultEntity.SUCCESS)) {
			return "/member/login";
		}
		if(result.equals(ResultEntity.FAILED)){
			model.addAttribute("message",doRegisterRemote.getMessage());
			return "/member/register";
		}
		return "/member/register";
	}
	
	@RequestMapping("/member/login")
	public String login(Member member,Model model,HttpSession session){
		ResultEntity<Member> loginRemote = memberRemoteService.doLoginRemote(member);
		String result = loginRemote.getResult();
		if (result.equals(ResultEntity.SUCCESS)) {
			session.setAttribute("LOGIN_MEMBER",loginRemote.getDataT());
			return "/member/member";
		}
		
		if (result.equals(ResultEntity.FAILED)) {
			model.addAttribute("MESSAGE",loginRemote.getMessage());
			return "/member/login";
		}
		return "/member/login";
	}
}
