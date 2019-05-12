package com.revature.services;

import java.security.NoSuchAlgorithmException;

import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.daos.LoginDao;
import com.revature.daos.RequestDao;
import com.revature.util.HttpException;

public class LoginService {
	LoginDao loginDao = new LoginDao();
	public static int userId;
	public static int userRoleId;

	public int login(Credentials credentials) throws NoSuchAlgorithmException {
		
		// 1. If credentials lacks a password, throw HttpException with status 422
		// 2. If credentials lacks a username, throw HttpException with status 422
		if(credentials.getHashedPassword() == null || credentials.getUsername() == null) {
			throw new HttpException(422);
		}
		
		// attempt to retrieve username/password from database
		User user = loginDao.getPasswordByUsername(credentials.getUsername());
		// compare them
		// 3. If returned user password does not match credentials password, throw
		//   	HttpException with status 400
		if (!user.getPassword().equals(credentials.getHashedPassword())) {
			throw new HttpException(400);
		}
		
		userRoleId = user.getRoleId();
		userId = user.getId();
		
		// 4. if returned user password matches credentials password, return integer
		// 		value equal to the id on the returned user object
		return user.getId();
	}
	
	public int getRole(Credentials credentials) throws NoSuchAlgorithmException{
		User user = loginDao.getPasswordByUsername(credentials.getUsername());
		return user.getRoleId();
	}
	
	public String getFirstName(Credentials credentials) throws NoSuchAlgorithmException{
		User user = loginDao.getPasswordByUsername(credentials.getUsername());
		return user.getFirstName();
	}
	
	public String getLastName(Credentials credentials) throws NoSuchAlgorithmException{
		User user = loginDao.getPasswordByUsername(credentials.getUsername());
		return user.getLastName();
	}


	public LoginService(LoginDao loginDao) {
		super();
		this.loginDao = loginDao;
	}

	public LoginService() {
		super();
		this.loginDao = new LoginDao();
	}
}
