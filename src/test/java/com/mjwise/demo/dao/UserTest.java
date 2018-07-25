package com.mjwise.demo.dao;

import com.mjwise.demo.entity.systerm.SystemUser;
import com.mjwise.demo.service.system.SystemUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	SystemUserService UserService = new SystemUserService();

	@Test
	public void addUserTest(){
		String username = "aaa";
		String password = "1234";
		UserService.save(username,password);
		SystemUser user =  UserService.find(username);
		System.out.println(user.getId()+"------"+user.getUserName()+"-----"+user.getPassword());
	}

	@Test
	public void modifyUserTest(){
		UserService.update("aaa","asdfasddfsaf");
		SystemUser user =  UserService.find("aaa");
		System.out.println(user.getId()+"------"+user.getUserName()+"-----"+user.getPassword());
	}

	@Test
	public void deleteUserTest(){
		SystemUser user = UserService.find("aaa");
		if(user!=null){
			UserService.delete("aaa");
			SystemUser user1 =  UserService.find("aaa");
			if (user1 == null){
				System.out.println("成功");
			}
		}else {
			System.out.println("失败");
		}
	}
}
