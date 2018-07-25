package com.mjwise.demo.service.system;

import com.mjwise.demo.entity.systerm.SystemUser;
import com.mjwise.demo.repository.system.SystemUserRepository;
import com.mjwise.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;
import java.util.List;

@Service
public class SystemUserService extends BaseService<SystemUser,String> {
	@Autowired
	SystemUserRepository systemUserRepository;

	public SystemUser find(String username,String password){
		if(username!=null && password!=null){
			return systemUserRepository.findSystemUserByUserNameAndPassword(username, password);
		}
		return null;
	}


	public SystemUser find(String username){
		if(username!=null){
			return systemUserRepository.findSystemUserByUserName(username);
		}
		return null;
	}

	public void save(String username, String password){
		if(username!=null && password!=null) {
			systemUserRepository.saveAndFlush(new SystemUser(username, password));
		}
	}


	public void delete(String username){
		if(username!=null){
			SystemUser user = systemUserRepository.findSystemUserByUserName(username);
			systemUserRepository.delete(user);
		}
	}

	public void update(String username, String password){
		SystemUser user = systemUserRepository.findSystemUserByUserName(username);
		if(user!=null){
			delete(username);
			save(username,password);
		}
	}
}
