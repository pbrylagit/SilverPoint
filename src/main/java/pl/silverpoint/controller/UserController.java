package pl.silverpoint.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.silverpoint.domain.User;
import pl.silverpoint.exception.UserNotFoundException;
import pl.silverpoint.service.UserService;

@Controller
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String usersList(Model model){
		model.addAttribute("userList", userService.list());
		return "users/users";
	}
	
	@RequestMapping(value="/b")
	public String usersListBlocks(Model model){
		model.addAttribute("userList", userService.list());
		return "users/usersBlocks";
	}
	
	@RequestMapping(value= "/add", method = RequestMethod.GET)
	public String addNewUser(Model model){
		User user = new User();
		model.addAttribute("newUser", user);
		return "users/addUser";
	}
	
	@RequestMapping(value= "/add", method = RequestMethod.POST)
	public String processAddNewUserFrom(@ModelAttribute("newUser") @Valid User newUser, BindingResult result){
		if(result.hasErrors()){
			return "users/addUser";
		}
		userService.addUser(newUser);
		return "redirect:/users";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam("id") int userId, Model model){
		model.addAttribute("user", userService.getUserById(userId));
		return "users/editUser";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute ("user") User user){
		userService.updateUser(user);
		return "redirect:/users";
	}
	
	@RequestMapping(value="/delete")
	public String deleteUser(@RequestParam("id") int userId){
		User userToDelete = userService.getUserById(userId);
		userService.deleteUser(userToDelete.getUserId());
		return "redirect:/users";
	}
	
	@RequestMapping(value="/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal){
		String username = principal.getName();
		model.addAttribute("user", username);
		return "error403";
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public String handleError(Model model, UserNotFoundException exception){
		model.addAttribute("invalidUserId", exception.getUserId());
		return "users/userNotFound";
	}

}
