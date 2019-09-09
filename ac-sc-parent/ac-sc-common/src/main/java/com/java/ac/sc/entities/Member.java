package com.java.ac.sc.entities;

public class Member {
    private Integer memberId;

    private String loginAcc;

    private String loginPwd;

    private String nickName;

    private String realName;

    private String cardNum;

    private String phoneNum;

    private String emailAddr;

    private Byte authStatus = 0;

    private Byte accType;

    private Byte company;

    private String extralFieldA;

    private String extralFieldB;

    private String extralFieldC;

    private String extralFieldD;
    
    

    @Override
	public String toString() {
		return "Member [memberId=" + memberId + ", loginAcc=" + loginAcc + ", loginPwd=" + loginPwd + ", nickName="
				+ nickName + ", realName=" + realName + ", cardNum=" + cardNum + ", phoneNum=" + phoneNum
				+ ", emailAddr=" + emailAddr + ", authStatus=" + authStatus + ", accType=" + accType + ", company="
				+ company + ", extralFieldA=" + extralFieldA + ", extralFieldB=" + extralFieldB + ", extralFieldC="
				+ extralFieldC + ", extralFieldD=" + extralFieldD + "]";
	}

	public Member() {
		super();
	}

	public Member(Integer memberId, String loginAcc, String loginPwd, String nickName, String realName, String cardNum,
			String phoneNum, String emailAddr, Byte authStatus, Byte accType, Byte company, String extralFieldA,
			String extralFieldB, String extralFieldC, String extralFieldD) {
		super();
		this.memberId = memberId;
		this.loginAcc = loginAcc;
		this.loginPwd = loginPwd;
		this.nickName = nickName;
		this.realName = realName;
		this.cardNum = cardNum;
		this.phoneNum = phoneNum;
		this.emailAddr = emailAddr;
		this.authStatus = authStatus;
		this.accType = accType;
		this.company = company;
		this.extralFieldA = extralFieldA;
		this.extralFieldB = extralFieldB;
		this.extralFieldC = extralFieldC;
		this.extralFieldD = extralFieldD;
	}

	public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getLoginAcc() {
        return loginAcc;
    }

    public void setLoginAcc(String loginAcc) {
        this.loginAcc = loginAcc == null ? null : loginAcc.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr == null ? null : emailAddr.trim();
    }

    public Byte getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Byte authStatus) {
        this.authStatus = authStatus;
    }

    public Byte getAccType() {
        return accType;
    }

    public void setAccType(Byte accType) {
        this.accType = accType;
    }

    public Byte getCompany() {
        return company;
    }

    public void setCompany(Byte company) {
        this.company = company;
    }

    public String getExtralFieldA() {
        return extralFieldA;
    }

    public void setExtralFieldA(String extralFieldA) {
        this.extralFieldA = extralFieldA == null ? null : extralFieldA.trim();
    }

    public String getExtralFieldB() {
        return extralFieldB;
    }

    public void setExtralFieldB(String extralFieldB) {
        this.extralFieldB = extralFieldB == null ? null : extralFieldB.trim();
    }

    public String getExtralFieldC() {
        return extralFieldC;
    }

    public void setExtralFieldC(String extralFieldC) {
        this.extralFieldC = extralFieldC == null ? null : extralFieldC.trim();
    }

    public String getExtralFieldD() {
        return extralFieldD;
    }

    public void setExtralFieldD(String extralFieldD) {
        this.extralFieldD = extralFieldD == null ? null : extralFieldD.trim();
    }
}