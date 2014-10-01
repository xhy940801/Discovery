package com.discovery.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.discovery.service.friend.dao.RequestForFriendDAO;
import com.discovery.service.friend.model.RelationOfFriend;
import com.discovery.service.friend.model.RequestForFriend;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false) 
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:beans.xml"})
public class RequestTest extends AbstractTransactionalJUnit4SpringContextTests {

	private RequestForFriendDAO requestForFriendDAO;
	
	@Before
	public void setUp() throws Exception {
		requestForFriendDAO = (RequestForFriendDAO)this.applicationContext.getBean("requestForFriendDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

	//@Test
	public void test() {
		RequestForFriend rff;
		for(int i = 0;i < 10; i++){
			rff = new RequestForFriend();
			rff.setSponsorId(2+i);
			rff.setReceiverId(2);
			rff.setRequestTime(new Date(2014-1900,10-1,2));
			requestForFriendDAO.save(rff);
		}
	}
	
	//@Test
	public void testDelete() {
		
		requestForFriendDAO.delete(2, 2);
	}
	
	//@Test
	public void testCheck(){
		boolean check = requestForFriendDAO.checkout(2, 2);
		if(check){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}
	
	//@Test
	public void testGet1() {
		List<RequestForFriend> list = requestForFriendDAO.getToRequset(1);
		for(RequestForFriend rof : list){
			System.out.println(rof.getReceiverId());
		}
	}

	@Test
	public void testGet2() {
		List<RequestForFriend> list = requestForFriendDAO.getFromRequest(2);
		for(RequestForFriend rof : list){
			System.out.println(rof.getSponsorId());
		}
	}
}
