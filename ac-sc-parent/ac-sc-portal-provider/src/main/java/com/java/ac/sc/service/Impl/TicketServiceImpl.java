package com.java.ac.sc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.ac.sc.entities.Ticket;
import com.java.ac.sc.mapper.TicketMapper;
import com.java.ac.sc.service.TicketService;

@Service
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketMapper ticketMapper;

	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int removeByPrimaryKey(Integer id) {
		return ticketMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int save(Ticket record) {
		return ticketMapper.insert(record);
	}

	@Override
	public Ticket getByPrimaryKey(Integer id) {
		return ticketMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Ticket> getAll() {
		return ticketMapper.selectAll();
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int updateByPrimaryKey(Ticket record) {
		return ticketMapper.updateByPrimaryKey(record);
	}

	@Override
	public Ticket getByMemberId(Integer memberId) {
		return ticketMapper.selectByMemberId(memberId);
	}
	
	@Override
	@Transactional(readOnly = false,noRollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	public int updatePstepByMemberId(Integer memberId, String pstep) {
		return ticketMapper.updatePstepByMemberId(memberId, pstep);
	}

	@Transactional(readOnly = false,noRollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
	@Override
	public int updatePstepAndCodeByMemberId(Integer memberId, String pstep, String checkCode) {
		return ticketMapper.updatePstepAndCodeByMemberId(memberId, pstep, checkCode);
	}

}
