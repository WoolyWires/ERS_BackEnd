package services;

import daos.UserDao;
import models.User;

public class UserService {

	UserDao userDao = new UserDao();
	
	public User findUser(int userId) {
		return userDao.findUser(userId);
	}
	
	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}
	
	public Object findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}
	
	public User findUserByLogin(String username, String password) {
		return userDao.findUserByLogin(username, password);
	}

}
