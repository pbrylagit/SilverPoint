package pl.silverpoint.service;

import java.util.List;

import pl.silverpoint.domain.User;

public interface UserService {

	List<User> list();

	List<User> employeesList();
	
	void addUser(User newUser);

	User getUserById(int userId);

	void deleteUser(int userId);

	void updateUser(User editedUser);
	
	List<User> checkIfEmailExist(String email);
	
	List<User> checkIfUsernameExist(String username);
}
