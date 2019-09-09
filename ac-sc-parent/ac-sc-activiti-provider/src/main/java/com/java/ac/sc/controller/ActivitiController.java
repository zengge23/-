package com.java.ac.sc.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.ResultEntity;
import com.java.ac.sc.service.MemberService;

@RestController
public class ActivitiController {
	
	@Autowired
	RepositoryService repositoryService;

	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/finish/process/{taskId}/{flag}")
	public ResultEntity<String> finishProcess(@PathVariable("taskId")String taskId, @PathVariable("flag")Boolean flag) {
		ResultEntity<String> resultEntity = new ResultEntity<>();
		try {
			HashMap<String, Object> variables = new HashMap<>();
			variables.put("flag",flag);
			taskService.complete(taskId,variables);
			resultEntity.setResult(ResultEntity.SUCCESS);
		} catch (Exception e) {
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return resultEntity;
	}
	
	@RequestMapping("/get/approval/taskList/remote")
	public ResultEntity<List<Map<String,String>>> getApprovalTaskListRemote(){
		ResultEntity<List<Map<String,String>>> resultEntity = new ResultEntity<>();
		try {
			String processDefinitionKey = "AcProcess";
			String candidateGroup = "processAdmin";
			List<Task> taskList = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskCandidateGroup(candidateGroup).list();
			List<Map<String,String>> resultMapList = new ArrayList<>();
			for (Task task : taskList) {
				String taskId = task.getId();
				String taskName = task.getName();
				String processInstanceId = task.getProcessInstanceId();
				Member member = memberService.getMemberListByprocessInstanceId(processInstanceId);
				
				Map<String, String> resultMap = new HashMap<>();
				resultMap.put("taskId", taskId);
				resultMap.put("taskName", taskName);
				resultMap.put("memberId", member.getMemberId()+"");
				resultMap.put("nickName", member.getNickName());
				
				resultMapList.add(resultMap);
			}
			resultEntity.setDataT(resultMapList);
			resultEntity.setResult(ResultEntity.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}
		return resultEntity;
	}
	
	@RequestMapping("complete/member/task3/remote/{loginAcc}/{emailAddress}/{bool}/{checkCode}")
	public ResultEntity<String> completeMemberTask3Remote(@PathVariable("loginAcc") String loginAcc,@PathVariable("emailAddress") String emailAddress,@PathVariable("bool") Boolean bool,@PathVariable("checkCode") String checkCode) {
		ResultEntity<String> resultEntity = new ResultEntity<>();
		try {
			String processDefinitionKey = "AcProcess";
			Map<String, Object> variables = new HashMap<>();
			variables.put("member",loginAcc);
			variables.put("memberEmail",emailAddress);
			variables.put("flag",bool);
			variables.put("code",checkCode);
			String taskId = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(loginAcc).singleResult().getId();
			taskService.complete(taskId,variables);
			resultEntity.setResult(ResultEntity.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}
		return resultEntity;
	}
	
	@RequestMapping("/complete/member/task/remote/{loginAcc}")
	public ResultEntity<String> completeMemberTaskRemote(@PathVariable("loginAcc")String loginAcc) {
		ResultEntity<String> resultEntity = new ResultEntity<>();
		try {
			String processDefinitionKey = "AcProcess";
			Map<String, Object> variables = new HashMap<>();
			variables.put("member",loginAcc);
			String taskId = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(loginAcc).singleResult().getId();
			taskService.complete(taskId,variables);
			resultEntity.setResult(ResultEntity.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}
		return resultEntity;
	}
	
	@RequestMapping("/complete/member/task2/remote/{loginAcc}/{bool}")
	public ResultEntity<String> completeMemberTask2Remote(@PathVariable("loginAcc")String loginAcc,@PathVariable("bool")Boolean bool) {
		ResultEntity<String> resultEntity = new ResultEntity<>();
		try {
			String processDefinitionKey = "AcProcess";
			Map<String, Object> variables = new HashMap<>();
			variables.put("member",loginAcc);
			variables.put("flag",bool);
			String taskId = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(loginAcc).singleResult().getId();
			taskService.complete(taskId,variables);
			resultEntity.setResult(ResultEntity.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}
		return resultEntity;
	}
	
	@RequestMapping("/start/process/instance/remote/{loginAcc}")
	public ResultEntity<String> startRealNameAuthProcessInstance(@PathVariable("loginAcc")String loginAcc){
		ResultEntity<String> resultEntity = new ResultEntity<>();
		try {
			String processDefinitionKey = "AcProcess";
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).latestVersion().singleResult();
			String processDefinitionId = processDefinition.getId();
			Map<String, Object> variables = new HashMap<>();
			variables.put("member",loginAcc);
			ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId,variables);
			String processInstanceId = processInstance.getId();
			resultEntity.setResult(ResultEntity.SUCCESS);
			resultEntity.setDataT(processInstanceId);
		} catch (Exception e) {
			e.printStackTrace();
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}
		return resultEntity;
	}
	
	@RequestMapping("/get/process/definition/list/remote")
	public ResultEntity<List<Map<String,Object>>> getProcessDefinitionListRemote(){
		ResultEntity<List<Map<String,Object>>> resultEntity = new ResultEntity<>();
		try {
			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
			List<Map<String,Object>> processDefinitionMapList = new ArrayList<>();
			for (ProcessDefinition processDefinition : list) {
				String id = processDefinition.getId();
				String name = processDefinition.getName();
				String key = processDefinition.getKey();
				int version = processDefinition.getVersion();
				
				Map<String,Object> hashMap = new HashMap<>();
				hashMap.put("id",id);
				hashMap.put("name",name);
				hashMap.put("key",key);
				hashMap.put("version",version);
				processDefinitionMapList.add(hashMap);
			}
			resultEntity.setResult(ResultEntity.SUCCESS);
			resultEntity.setDataT(processDefinitionMapList);
		} catch (Exception e) {
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return resultEntity;
	}

	@RequestMapping("/deploy/process/definition/file/remote")
	public String deployProcessDefinitionFile(@RequestParam("resource") MultipartFile resource) throws IOException {
		try {
			InputStream inputStream = resource.getInputStream();
			String originalFilename = resource.getOriginalFilename();
			//基于传入的流程定义文件的输入流执行部署
			repositoryService.createDeployment().addInputStream(originalFilename,inputStream).deploy();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "SUCCESS";
	}
	
	@RequestMapping("/get/process/definition/image/{name}/{version}")
	public byte[] eyeImage(@PathVariable("name") String name,@PathVariable("version") Integer version) throws IOException {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(name).processDefinitionVersion(version).singleResult();
		String deploymentId = processDefinition.getDeploymentId();
		String diagramResourceName = processDefinition.getDiagramResourceName();
		InputStream inputStream = repositoryService.getResourceAsStream(deploymentId,diagramResourceName);
		byte[] buffer = new byte[100];
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int len = 0;
		while((len = inputStream.read(buffer,0,100)) > 0) {
			swapStream.write(buffer,0,len);
		}
		byte[] pngImageByteArray = swapStream.toByteArray();
		swapStream.close();
		return pngImageByteArray;
	}
}
