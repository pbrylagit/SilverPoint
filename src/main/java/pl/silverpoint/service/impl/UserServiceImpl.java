package pl.silverpoint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.silverpoint.dao.UserDao;
import pl.silverpoint.domain.User;
import pl.silverpoint.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public List<User> employeesList() {
		return userDao.employeesList();
	}
	
	@Override
	public void addUser(User newUser) {
		userDao.addUser(newUser);
	}

	@Override
	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public void deleteUser(int userId) {
		userDao.deleteUser(userId);
		
	}

	@Override
	public void updateUser(User editedUser) {
		userDao.updateUser(editedUser);
	}
	
	@Override
	public List<User> checkIfEmailExist(String email) {
		return userDao.checkIfEmailExist(email);
	}

	@Override
	public List<User> checkIfUsernameExist(String username) {
		return userDao.checkIfUsernameExist(username);
	}
}
