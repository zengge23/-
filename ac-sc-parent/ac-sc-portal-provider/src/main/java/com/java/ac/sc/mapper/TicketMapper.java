package com.java.ac.sc.mapper;

import com.java.ac.sc.entities.Ticket;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    Ticket selectByPrimaryKey(Integer id);

    List<Ticket> selectAll();

    int updateByPrimaryKey(Ticket record);

	Ticket selectByMemberId(Integer memberId);

	int updatePstepByMemberId(@Param("memberId")Integer memberId, @Param("pstep")String pstep);

	int updatePstepAndCodeByMemberId(@Param("memberId")Integer memberId, @Param("pstep")String pstep, @Param("authcode")String checkCode);

}