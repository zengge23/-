package com.java.ac.sc.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.java.ac.sc.entities.ResultEntity;
import com.java.ac.sc.service.ManagerProviderRemoteService;
import com.java.ac.sc.service.ProcessRemoteService;

@Controller
public class ProcessesController {
	
	@Autowired
	ProcessRemoteService processRemoteService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ManagerProviderRemoteService managerProviderRemoteService;
	
	@ResponseBody
	@RequestMapping("/consumer/process/do/approval")
	public ResultEntity<String> doApproval(
			@RequestParam("result") String result, 
			@RequestParam("taskId") String taskId, 
			@RequestParam("memberId") Integer memberId) {
		
		Boolean flag = null;
		if("access".equals(result)) {
			flag = true;
		}
		if("deny".equals(result)) {
			flag = false;
		}
		
		processRemoteService.finishProcess(taskId, flag);
		
		managerProviderRemoteService.updateTicketStatusForProcessFinish(memberId);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
	@RequestMapping("/consumer/process/detail/{memberId}")
	public String getDetailRemote(@PathVariable("memberId") Integer memberId,Model model){
		
		ResultEntity<Map<String, Object>> resultEntity = managerProviderRemoteService.getDetailRemote(memberId);
		String result = resultEntity.getResult();
		if (result.equals(ResultEntity.SUCCESS)) {
			Map<String, Object> detailMap = resultEntity.getDataT();
			model.addAttribute("detailMap",detailMap);
			
			return "process/detail";
		}else {
			model.addAttribute("MESSAGE",resultEntity.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("/consumer/get/approval/task/list")
	public String consumerGetApprovalTtaskList(Model model) {
		ResultEntity<List<Map<String,Object>>> resultEntity = processRemoteService.getApprovalTaskListRemote();
		List<Map<String,Object>> list = resultEntity.getDataT();
		model.addAttribute("taskList",list);
		return "process/task_list";
	}

	@RequestMapping("/consumer/process/list")
	public String showProcessDefinitionList(Model model) {
		
		ResultEntity<List<Map<String, Object>>> processDefinitionListRemote = processRemoteService.getProcessDefinitionListRemote();
		String result = processDefinitionListRemote.getResult();
		
		if (result.equals("SUCCESS")) {
			List<Map<String,Object>> processDefinitionList = processDefinitionListRemote.getDataT();
			model.addAttribute("processDefinitionList",processDefinitionList);
			return "process/definition_list";
		}else {
			model.addAttribute("message",processDefinitionListRemote.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("/consumer/process/upload")
	public String uploadProcessDefinitionFile(@RequestParam("bpmnFile") MultipartFile bpmnFile,Model model) throws IOException {
		
		String tempFileName = UUID.randomUUID().toString();
		String tempExtName = bpmnFile.getOriginalFilename().substring(bpmnFile.getOriginalFilename().lastIndexOf("."));
		if (!tempExtName.equals(".bpmn")) {
			model.addAttribute("MESSAGE","请上传流程定义文件! 其它文件将不予识别");
			return "error";
		}
		File tempFile = File.createTempFile(tempFileName,tempExtName);
		bpmnFile.transferTo(tempFile);
		FileSystemResource resource = new FileSystemResource(tempFile);
		LinkedMultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
		paramMap.add("resource",resource);
		
		String url = "http://ActivitiProvider/deploy/process/definition/file/remote";
		String response = restTemplate.postForObject(url,paramMap,String.class);
		System.err.println(response);
		
		if ("SUCCESS".equals(response)) {
			return "redirect:/consumer/process/list";
		}else {
			model.addAttribute("MESSAGE",response);
			return "error";
		}
	}

	@RequestMapping("/consumer/process/definition/eye/{name}/{version}")
	public void eyeImage(@PathVariable("name") String name,@PathVariable("version") String version,HttpServletResponse responseFromConsumeToBrowser) throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_PNG);
		String url = "http://ActivitiProvider/get/process/definition/image/"+name+"/"+version;
		ResponseEntity<byte[]> responseFromProviderToConsumer = restTemplate.exchange(url,HttpMethod.POST,new HttpEntity<byte[]>(httpHeaders),byte[].class);
		byte[] byteArray = responseFromProviderToConsumer.getBody();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
		ServletOutputStream outputStream = responseFromConsumeToBrowser.getOutputStream();
		int intDate = 0;
		while((intDate = inputStream.read()) != -1) {
			outputStream.write(intDate);
		}
	}
	
}
