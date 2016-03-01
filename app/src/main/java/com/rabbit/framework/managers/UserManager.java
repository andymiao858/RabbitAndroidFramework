package com.rabbit.framework.managers;

import com.rabbit.framework.models.bo.UserBo;

/**
 * @author miaohd
 */
public class UserManager {

	private UserBo user = new UserBo();

	private UserManager(){}

	public static UserManager getInstance(){
		return UserManagerHolder._instance;
	}

	private static class UserManagerHolder{
		private static final UserManager _instance = new UserManager();
	}

	public UserBo getUser() {
		return user;
	}
}
