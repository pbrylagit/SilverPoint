package pl.silverpoint.controller;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import pl.silverpoint.domain.User;
import pl.silverpoint.exception.UserNotFoundException;
import pl.silverpoint.service.UserService;

public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void test_list() throws Exception {
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());

		when(userService.list()).thenReturn((List<User>) users);

		mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(view().name("users/users"))
				.andExpect(model().attribute("userList", hasSize(2)));

		verify(userService, times(1)).list();

	}

	@Test
	public void test_list_b() throws Exception {
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());

		when(userService.list()).thenReturn((List<User>) users);

		mockMvc.perform(get("/users/b")).andExpect(status().isOk()).andExpect(view().name("users/usersBlocks"))
				.andExpect(model().attribute("userList", hasSize(2)));

		verify(userService, times(1)).list();
	}

	@Test
	public void test_add_new_user_get() throws Exception {

		mockMvc.perform(get("/users/add")).andExpect(status().isOk()).andExpect(view().name("users/addUser"))
				.andExpect(model().attribute("newUser", instanceOf(User.class)));

	}

	@Test
	public void test_add_new_user_post() throws Exception {
		mockMvc.perform(post("/users/add")
				.param("email", "test@silverpoint.pl")
				.param("firstName", "John")
				.param("lastName", "Black")
				.param("password", "102938456")
				.param("username", "jblack")
				.param("result", "true")
				.sessionAttr("newUser", instanceOf(User.class)))
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeHasNoErrors("newUser"))
				.andExpect(redirectedUrl("/users"))
				.andExpect(status().is3xxRedirection());
		verify(userService, times(1)).addUser(any());
	}

	@Test
	public void test_add_new_user_post_with_result_has_error() throws Exception {
		mockMvc.perform(post("/users/add").param("firstName", "John").param("lastName", "Black")
				.param("password", "102938456").sessionAttr("newUser", new User()))
				.andExpect(model().errorCount(2)).andExpect(model().hasErrors()).andExpect(view().name("users/addUser"))
				.andExpect(status().isOk());
	}

	@Test
	public void test_edit_user() throws Exception {

		mockMvc.perform(get("/users/edit").param("id", "7")).andExpect(status().isOk())
				.andExpect(view().name("users/editUser"));
	}

	@Test
	public void test_update_user() throws Exception {
		mockMvc.perform(post("/users/edit"))
				.andExpect(redirectedUrl("/users"))
				.andExpect(status().is3xxRedirection());
		verify(userService, times(1)).updateUser(any());
	}

	@Test
	public void test_delete_User() throws Exception {
		mockMvc.perform(get("/users/delete").param("id", "7")).andExpect(redirectedUrl("/users"))
				.andExpect(status().is3xxRedirection());
		verify(userService, times(1)).deleteUser(7);
	}

	@Test
	public void test_access_denied() throws Exception {
		User user = getUser();
		Principal mockPrincipal = mock(Principal.class);
		when(mockPrincipal.getName()).thenReturn(user.getUsername());
		mockMvc.perform(get("/users/403").principal(mockPrincipal)).andExpect(model().attributeExists("user"))
				.andExpect(view().name("error403")).andExpect(status().isOk());
	}

	@Test
	public void test_handle_error() {
		User user = getUser();
		UserNotFoundException exception = mock(UserNotFoundException.class);
		Model model = mock(Model.class);
		when(exception.getUserId()).thenReturn(user.getUserId());
		String result = userController.handleError(model, exception);
		assertEquals("users/userNotFound", result);
	}

	private User getUser() {
		User user = new User();
		user.setEmail("jblack@silverpoint.pl");
		user.setFirstName("John");
		user.setLastName("Black");
		user.setPassword("1234");
		user.setUserId(7);
		user.setUsername("jblack");

		return user;
	}
}
