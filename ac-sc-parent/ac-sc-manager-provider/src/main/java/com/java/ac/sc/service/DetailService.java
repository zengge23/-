package com.java.ac.sc.service;

import java.util.List;
import java.util.Map;

import com.java.ac.sc.entities.Member;

public interface DetailService {

	Member getMemberByMemberId(Integer memberId);

	List<Map<String, String>> getMemberCertDetail(Integer memberId);

}
