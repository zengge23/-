package com.java.ac.sc.mapper;

import com.java.ac.sc.entities.MemberCert;
import java.util.List;

public interface MemberCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberCert record);

    MemberCert selectByPrimaryKey(Integer id);

    List<MemberCert> selectAll();

    int updateByPrimaryKey(MemberCert record);

	int insertList(List<MemberCert> memberParamList);
}