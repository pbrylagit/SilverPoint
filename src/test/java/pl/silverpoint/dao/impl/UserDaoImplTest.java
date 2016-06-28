package pl.silverpoint.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.silverpoint.domain.User;
import pl.silverpoint.exception.UserNotFoundException;

public class UserDaoImplTest {

	@Mock
	private SessionFactory mockSessionFactory;
	
	@Mock
	private Session mockSession;
	
	@Mock
	private Transaction mockTransaction;
	
	@InjectMocks
	private UserDaoImpl userDao;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		when(mockSessionFactory.openSession()).thenReturn(mockSession);
	}
	
	@After
	public void close(){
		mockSession.close();
	}
	
	@Test
	public void test_list() {
		List<User> usersList = new ArrayList<>();
		usersList.add(getUser());
		Query mockQuery = mock(Query.class);
		when(mockSession.createQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.list()).thenReturn(usersList);
		assertEquals(usersList, userDao.list());
		verify(mockSession.createQuery(anyString()), times(1)).list();
	}
	
	@Test
	public void test_employeeList(){
		List<User> usersList = new ArrayList<>();
		usersList.add(getUser());
		Query mockQuery = mock(Query.class);
		when(mockSession.createQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.list()).thenReturn(usersList);
		assertEquals(usersList, userDao.employeesList());
		verify(mockSession.createQuery(anyString()), times(1)).list();
	}
	
	@Test
	public void test_add_user(){
		User user = getUser();
		when(mockSession.beginTransaction()).thenReturn(mockTransaction);
		userDao.addUser(user);
		verify(mockSession, times(1)).save(user);
		verify(mockTransaction, times(1)).commit();
	}
	
	@Test
	public void test_get_uesr_by_id(){
		User user = getUser();
		when(mockSession.get(User.class, 7)).thenReturn(user);
		assertEquals(user, userDao.getUserById(7));
		verify(mockSession, times(1)).get(User.class, 7);
	}
	
	@Test(expected = UserNotFoundException.class)
	public void test_get_user_by_id_should_throw_user_not_found_exception(){
		when(mockSession.get(User.class, 7)).thenReturn(null);
		assertEquals(null, userDao.getUserById(7));
		verify(mockSession, times(1)).get(User.class, 7);
	}
	
	@Test
	public void test_update_user(){
		User user = getUser();
		when(mockSession.beginTransaction()).thenReturn(mockTransaction);
		userDao.updateUser(user);
		verify(mockSession, times(1)).update(user);
		verify(mockTransaction, times(1)).commit();
	}
	
	@Test
	public void test_delete_user(){
		User user = getUser();
		when(mockSession.beginTransaction()).thenReturn(mockTransaction);
		when(mockSession.load(User.class, 7)).thenReturn(user);
		userDao.deleteUser(7);
		verify(mockSession, times(1)).delete(user);
		verify(mockTransaction, times(1)).commit();
	}
	
	@Test
	public void test_check_if_email_exist(){
		List<User> usersList = new ArrayList<>();
		usersList.add(getUser());
		String email = "jblack@silverpoint.pl";
		Query mockQuery = mock(Query.class);
		when(mockSession.createQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.setParameter("email", email)).thenReturn(mockQuery);
		when(mockQuery.list()).thenReturn(usersList);
		userDao.checkIfEmailExist(email);
		assertEquals("jblack@silverpoint.pl", usersList.get(0).getEmail());
		verify(mockQuery, times(1)).list();
	}
	
	@Test
	public void test_check_if_username_exist(){
		List<User> usersList = new ArrayList<>();
		usersList.add(getUser());
		String username = "jblack";
		Query mockQuery = mock(Query.class);
		when(mockSession.createQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.setParameter("username", username)).thenReturn(mockQuery);
		when(mockQuery.list()).thenReturn(usersList);
		userDao.checkIfUsernameExist(username);
		assertEquals("jblack", usersList.get(0).getUsername());
		verify(mockQuery, times(1)).list();
	}
	
	public User getUser(){
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
