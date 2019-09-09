package com.java.ac.sc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.ac.sc.mapper.TicketMapper;
import com.java.ac.sc.service.TicketService;
@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketMapper ticketMapper;

	@Override
	public void updateTicketStatusForProcessFinish(Integer memberId) {
		ticketMapper.updateTicketStatusForProcessFinish(memberId);
	}

}
