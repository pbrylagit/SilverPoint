package pl.silverpoint.dao;

import java.util.List;

import pl.silverpoint.domain.User;

public interface UserDao {

	List<User> list();
	
	List<User> employeesList();

	void addUser(User newUser);

	User getUserById(int userId);
	
	void deleteUser(int userId);
	
	void updateUser(User editedUser);
	
	List<User> checkIfEmailExist(String value);
	
	List<User> checkIfUsernameExist(String username);
}
