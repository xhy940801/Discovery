package com.discovery.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.service.friend.dao.RelationOfFriendDAO;
import com.discovery.service.friend.model.RelationOfFriend;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false) 
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:beans.xml"})
public class RelationTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	private RelationOfFriendDAO relationOfFriendDAO;
	
	@Before
	public void setUp() throws Exception {
		 relationOfFriendDAO = (RelationOfFriendDAO) this.applicationContext.getBean("relationOfFriendDAO");
	}

	@After
	public void tearDown() throws Exception {
	}

	//@Test	
	@Transactional 
	public void test() {
		
		RelationOfFriend rof;
		for(int i = 0;i < 10; i++){
			rof = new RelationOfFriend();
			rof.setSponsorId(100);
			rof.setReceiverId(i+1);
			relationOfFriendDAO.save(rof);
		}
		
		
	//	fail("Not yet implemented");
	}
	
	//@Test	
	@Transactional 
	public void testDelete() {
		
		relationOfFriendDAO.delete(2, 1);
	//	fail("Not yet implemented");
	}
	
	//@Test
	@Transactional
	public void testGet(){
		
		List<RelationOfFriend> list = relationOfFriendDAO.getFriendsList(100);
		for(RelationOfFriend rof : list){
			System.out.println(rof.getReceiverId());
		}
	}
	
	@Test
	@Transactional
	public void testCheck(){
		boolean check = relationOfFriendDAO.checkout(100, 100);
		if(check){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}

}
