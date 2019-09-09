package com.java.ac.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.ac.sc.entities.Member;
import com.java.ac.sc.entities.ResultEntity;
import com.java.ac.sc.service.MemberService;
import com.java.ac.sc.utils.ACConst;
import com.java.ac.sc.utils.StringUtils;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/protal/provider/member/{id}")
	public ResultEntity<Member> getMemberById(@PathVariable("id") Integer id){
		Member member = memberService.getByPrimaryKey(id);
//		List<Member> all = memberService.getAll();
//		Member member = all.get(0);
		return new ResultEntity<Member>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,member);
	}
	
	@RequestMapping("/portal/provider/member/register")
	public ResultEntity<String> doRegisterRemote(@RequestBody Member member){
		ResultEntity<String> resultEntity = new ResultEntity<>();
		String loginAcc = member.getLoginAcc();
		String loginPwd = member.getLoginPwd();
		String md5 = StringUtils.md5(loginPwd);
		member.setLoginPwd(md5);
		try {
			int acc = memberService.getByloginAcc(loginAcc);
			if (acc == 0) {
				memberService.save(member);
				resultEntity.setResult(ResultEntity.SUCCESS);
			}else {
				resultEntity.setResult(ResultEntity.FAILED);
				resultEntity.setMessage(ACConst.THE_CURRENT_LOGIN_ACCOUNT_ALREADY_EXISTS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultEntity.setMessage(e.getMessage());
			resultEntity.setResult(ResultEntity.FAILED);
		}
		resultEntity.setDataT(ResultEntity.NO_DATA);
		return resultEntity;
	}

	@RequestMapping("/portal/provider/member/login")
	public ResultEntity<Member> doLoginRemote(@RequestBody Member member){
		ResultEntity<Member> resultEntity = new ResultEntity<>();
		try {
			Member memberDB = memberService.getByLoginAccAndLoginPwd(member);
			if (memberDB == null) {
				resultEntity.setResult(ResultEntity.FAILED);
				resultEntity.setMessage("用户名或密码错误!");
			}else {
				resultEntity.setResult(ResultEntity.SUCCESS);
				resultEntity.setDataT(memberDB);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultEntity.setMessage(e.getMessage());
			resultEntity.setResult(ResultEntity.FAILED);
		}
		return resultEntity;
	}
}
