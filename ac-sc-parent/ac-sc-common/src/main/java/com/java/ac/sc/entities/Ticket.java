package com.java.ac.sc.entities;

public class Ticket {
    private Integer id;

    private Integer memberid;

    private String piid;

    private String status;

    private String authcode;

    private String pstep;

    
    
    @Override
	public String toString() {
		return "Ticket [id=" + id + ", memberid=" + memberid + ", piid=" + piid + ", status=" + status + ", authcode="
				+ authcode + ", pstep=" + pstep + "]";
	}

	public Ticket() {
		super();
	}

	public Ticket(Integer id, Integer memberid, String piid, String status, String authcode, String pstep) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.piid = piid;
		this.status = status;
		this.authcode = authcode;
		this.pstep = pstep;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getPiid() {
        return piid;
    }

    public void setPiid(String piid) {
        this.piid = piid == null ? null : piid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode == null ? null : authcode.trim();
    }

    public String getPstep() {
        return pstep;
    }

    public void setPstep(String pstep) {
        this.pstep = pstep == null ? null : pstep.trim();
    }
}