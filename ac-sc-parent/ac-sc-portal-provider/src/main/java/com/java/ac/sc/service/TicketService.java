package com.java.ac.sc.service;

import java.util.List;

import com.java.ac.sc.entities.Ticket;

public interface TicketService {
	
	int removeByPrimaryKey(Integer id);

    int save(Ticket record);

    Ticket getByPrimaryKey(Integer id);

    List<Ticket> getAll();

    int updateByPrimaryKey(Ticket record);
    
    Ticket getByMemberId(Integer memberId);

	int updatePstepByMemberId(Integer memberId, String pstep);

	int updatePstepAndCodeByMemberId(Integer memberId, String pstep, String checkCode);

}
