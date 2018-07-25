package com.mjwise.demo.repository.system;

import com.mjwise.demo.entity.systerm.SystemUser;
import com.mjwise.demo.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends BaseRepository<SystemUser,String> {
	SystemUser findSystemUserByUserNameAndPassword(String userName,String password);
	SystemUser findSystemUserByUserName(String username);

}
