package com.java.ac.sc.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.ac.sc.entities.ResultEntity;

@FeignClient("ManagerProvider")
public interface ManagerProviderRemoteService {

	@RequestMapping("/get/detail/remote/{memberId}")
	ResultEntity<Map<String, Object>> getDetailRemote(@PathVariable("memberId")Integer memberId);

	@RequestMapping("/update/ticket/status/for/process/finish/{memberId}")
	ResultEntity<String> updateTicketStatusForProcessFinish(@PathVariable("memberId")Integer memberId);

}
