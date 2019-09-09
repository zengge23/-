package com.java.ac.sc.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.web.context.WebApplicationContext;

import com.java.ac.sc.config.IOCContainerReferenceProvider;
import com.java.ac.sc.service.MemberService;

public class DenyListener implements ExecutionListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.err.println("实名认证申请审核不通过!");
		
		WebApplicationContext iocContainerRef = IOCContainerReferenceProvider.getIOCContainerRef();
		MemberService memberService = iocContainerRef.getBean(MemberService.class);
		String processInstanceId = execution.getProcessInstanceId();
		String authStatus = "0";
		memberService.updateMemberAuthStatusByPiid(processInstanceId,authStatus);
	}

}
