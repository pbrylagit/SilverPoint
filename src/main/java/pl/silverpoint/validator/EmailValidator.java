package pl.silverpoint.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.silverpoint.domain.User;
import pl.silverpoint.service.UserService;

public class EmailValidator implements ConstraintValidator<Email, String> {

	@Autowired
	private UserService userService;

	@Override
	public void initialize(Email constraint) {

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		List<User> user;
		try {
			user = userService.checkIfEmailExist(email);
		} catch (Exception e) {
			return true;
		}
		if (user.size() != 0) {
			return false;
		}
		return true;
	}
}
