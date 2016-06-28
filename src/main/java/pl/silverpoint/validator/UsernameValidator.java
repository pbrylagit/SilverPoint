package pl.silverpoint.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.silverpoint.domain.User;
import pl.silverpoint.service.UserService;

public class UsernameValidator implements ConstraintValidator<Username, String> {

	@Autowired
	private UserService userService;

	@Override
	public void initialize(Username arg0) {

	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext arg1) {

		List<User> user;
		try {
			user = userService.checkIfUsernameExist(username);
		} catch (Exception e) {
			return true;
		}
		if (user.size() != 0) {
			return false;
		}
		return true;
	}
}
