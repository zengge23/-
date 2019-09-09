package com.java.ac.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.ac.sc.entities.ResultEntity;

@FeignClient("ActivitiProvider")
public interface ProcessRemoteService {
	
	@RequestMapping("/get/process/definition/list/remote")
	public ResultEntity<List<Map<String,Object>>> getProcessDefinitionListRemote();
	
	@RequestMapping("/start/process/instance/remote/{loginAcc}")
	public ResultEntity<String> startRealNameAuthProcessInstance(@PathVariable("loginAcc")String loginAcc);

	@RequestMapping("/complete/member/task/remote/{loginAcc}")
	public ResultEntity<String> completeMemberTaskRemote(@PathVariable("loginAcc")String loginAcc);

	@RequestMapping("/complete/member/task2/remote/{loginAcc}/{bool}")
	public ResultEntity<String> completeMemberTask2Remote(@PathVariable("loginAcc")String loginAcc, @PathVariable("bool")boolean b);

	@RequestMapping("/complete/member/task3/remote/{loginAcc}/{emailAddress}/{bool}/{checkCode}")
	public ResultEntity<String> completeMemberTask3Remote(@PathVariable("loginAcc") String loginAcc,@PathVariable("emailAddress") String emailAddress,@PathVariable("bool") Boolean bool,@PathVariable("checkCode")String checkCode);

	@RequestMapping("/get/approval/taskList/remote")
	public ResultEntity<List<Map<String,Object>>> getApprovalTaskListRemote();

	@RequestMapping("/finish/process/{taskId}/{flag}")
	public ResultEntity<String> finishProcess(@PathVariable("taskId")String taskId, @PathVariable("flag")Boolean flag);

}
