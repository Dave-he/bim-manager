package com.mjwise.demo.entity.systerm;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "system_user")
public class SystemUser {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", columnDefinition = "varchar(64) binary")
	private String id;

	/**
	 * 用户名
	 */
	@Column(name = "user_name",unique = true)
	private String userName;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;

	public SystemUser(){

	}

	public SystemUser(String username,String password) {
		this.userName = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}