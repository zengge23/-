package com.java.ac.sc.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.ResultEntity;

@FeignClient("PortalProvider")
public interface MemberRemoteService {

	@RequestMapping("/portal/provider/member/register")
	public ResultEntity<String> doRegisterRemote(@RequestBody Member member);
	
	@RequestMapping("/portal/provider/member/login")
	public ResultEntity<Member> doLoginRemote(@RequestBody Member member);
}
