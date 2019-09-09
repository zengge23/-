package com.java.ac.sc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.ResultEntity;
import com.java.ac.sc.service.DetailService;
import com.java.ac.sc.service.TicketService;

@RestController
public class ManagerController {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	DetailService detailService;
	
	@RequestMapping("/update/ticket/status/for/process/finish/{memberId}")
	ResultEntity<String> updateTicketStatusForProcessFinish(@PathVariable("memberId")Integer memberId){
		ResultEntity<String> resultEntity = new ResultEntity<>();
		try {
			ticketService.updateTicketStatusForProcessFinish(memberId);
			resultEntity.setResult(ResultEntity.SUCCESS);
		} catch (Exception e) {
			resultEntity.setMessage(e.getMessage());
			resultEntity.setResult(ResultEntity.FAILED);
			e.printStackTrace();
		}
		return resultEntity;
	}
	
	@RequestMapping("/get/detail/remote/{memberId}")
	ResultEntity<Map<String, Object>> getDetailRemote(@PathVariable("memberId")Integer memberId){
		ResultEntity<Map<String, Object>> resultEntity = new ResultEntity<>();
		Map<String, Object> resultMap;
		try {
			Member member = detailService.getMemberByMemberId(memberId);
			resultMap = new HashMap<>();
			
			resultMap.put("memberId", member.getMemberId());
			resultMap.put("nickName", member.getNickName());
			resultMap.put("realName", member.getRealName());
			resultMap.put("cardNum", member.getCardNum());
			resultMap.put("phoneNum", member.getPhoneNum());
			resultMap.put("emailAddr", member.getEmailAddr());
			List<Map<String, String>> detailMapList = detailService.getMemberCertDetail(memberId);
			resultMap.put("detailMapList",detailMapList);
			resultEntity.setDataT(resultMap);
			resultEntity.setResult(ResultEntity.SUCCESS);
		} catch (Exception e) {
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return resultEntity;
	}

}
