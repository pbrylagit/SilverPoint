package pl.silverpoint.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User testedObject;
	
	@Before
	public void setup(){
		testedObject = new User();
		testedObject.setEmail("test@silverpoint.pl");
		testedObject.setFirstName("John");
		testedObject.setLastName("Novak");
		testedObject.setPassword("1234");
		testedObject.setPermission("ROLE_USER");
		testedObject.setUserId(12);
		testedObject.setUsername("jnovak");
	}
	
	@Test
	public void testGetEmail(){
		assertEquals("test@silverpoint.pl", testedObject.getEmail());
	}
	
	@Test
	public void testGetFirstName(){
		assertEquals("John", testedObject.getFirstName());
	}
	
	@Test
	public void testGetLastName(){
		assertEquals("Novak", testedObject.getLastName());
	}
	
	@Test
	public void testGetPassword(){
		assertEquals("1234", testedObject.getPassword());
	}
	
	@Test
	public void testGetPermission(){
		assertEquals("ROLE_USER", testedObject.getPermission());
	}
	
	@Test
	public void testGetUserId(){
		assertEquals(12, testedObject.getUserId());
	}
	
	@Test
	public void testGetUsername(){
		assertEquals("jnovak", testedObject.getUsername());
	}
}
