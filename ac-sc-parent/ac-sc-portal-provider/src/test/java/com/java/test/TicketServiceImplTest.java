package com.java.test;

import org.junit.Test;

import com.java.ac.sc.entities.Ticket;
import com.java.ac.sc.service.Impl.TicketServiceImpl;

public class TicketServiceImplTest {

	@Test
	public void testGetByMemberId() {
		
		TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
//		Ticket byMemberId = ticketServiceImpl.getByMemberId(2);
		Ticket byMemberId = ticketServiceImpl.getByPrimaryKey(1);
		System.err.println(byMemberId);
	}

}
