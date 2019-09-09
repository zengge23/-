package com.java.ac.sc.entities;

import org.springframework.web.multipart.MultipartFile;

public class CertParam {
	
	private Integer certId;
	private MultipartFile certFile;
	
	public Integer getCertId() {
		return certId;
	}
	public void setCertId(Integer certId) {
		this.certId = certId;
	}
	public MultipartFile getCertFile() {
		return certFile;
	}
	public void setCertFile(MultipartFile certFile) {
		this.certFile = certFile;
	}
	
}
