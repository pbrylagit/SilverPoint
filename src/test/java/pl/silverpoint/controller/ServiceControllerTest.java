package pl.silverpoint.controller;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import pl.silverpoint.domain.Service;
import pl.silverpoint.domain.User;
import pl.silverpoint.exception.ServiceNotFoundException;
import pl.silverpoint.service.DataService;
import pl.silverpoint.service.UserService;

public class ServiceControllerTest {

	@Mock
	private DataService dataService;

	@Mock
	private UserService userService;
	
	@InjectMocks
	private ServiceController serviceController;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(serviceController).build();
	}

	@Test
	public void test_list() throws Exception {
		List<Service> service = new ArrayList<>();
		service.add(new Service());
		when(dataService.list()).thenReturn(service);
		mockMvc.perform(get("/service"))
				.andExpect(status().isOk())
				.andExpect(view().name("service/service"))
				.andExpect(model().attribute("serviceList", hasSize(1)));
		verify(dataService, times(1)).list();
	}

	@Test
	public void test_add_service_get() throws Exception {
		Principal principal = mock(Principal.class);
		List<User> users = new ArrayList<>();
		users.add(getUser());
		when(userService.employeesList()).thenReturn(users);
		mockMvc.perform(get("/service/add")
				.principal(principal))
				.andExpect(model().attribute("newService", instanceOf(Service.class)))
				.andExpect(model().attribute("adderUserName", principal.getName()))
				.andExpect(model().attribute("employees", users))
				.andExpect(view().name("service/addService"))
				.andExpect(status().isOk());
		verify(userService, times(1)).employeesList();
	}

	@Test
	public void test_add_service_post() throws Exception{
		mockMvc.perform(post("/service/add")
				.param("contact", "198-189-189")
				.param("description", "power adapter not charge")
				.param("owner", "Kowalski")
				.param("product", "notebook Lenovo")
				.param("status", "done")
				.sessionAttr("newService", instanceOf(Service.class)))
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeHasNoErrors("newService"))
				.andExpect(redirectedUrl("/service"))
				.andExpect(status().is3xxRedirection());
		verify(dataService, times(2)).getTodayDate();
		verify(dataService, times(1)).addService(any(Service.class));
	}
	
	@Test
	public void test_add_service_post_if_service_is_not_done() throws Exception{
		mockMvc.perform(post("/service/add")
				.param("contact", "198-189-189")
				.param("description", "power adapter not charge")
				.param("owner", "Kowalski")
				.param("product", "notebook Lenovo")
				.param("status", "notDone")
				.sessionAttr("newService", instanceOf(Service.class)))
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeHasNoErrors("newService"))
				.andExpect(redirectedUrl("/service"))
				.andExpect(status().is3xxRedirection());
		verify(dataService, times(1)).getTodayDate();
		verify(dataService, times(1)).addService(any(Service.class));
	}
	
	@Test
	public void test_add_service_post_with_result_has_error() throws Exception{
		mockMvc.perform(post("/service/add").param("contact", "198-189-189")
				.param("description", "power adapter not charge")
				.param("owner", "Kowalski")
				.param("status", "notDone")
				.sessionAttr("newService", instanceOf(Service.class)))
		.andExpect(model().errorCount(1))
		.andExpect(model().hasErrors())
		.andExpect(view().name("service/addService"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void test_edit_service() throws Exception{
		Service service = getService();
		List<User> users = new ArrayList<>();
		users.add(getUser());
		when(dataService.getServiceById(45L)).thenReturn(service);
		when(userService.employeesList()).thenReturn(users);
		mockMvc.perform(get("/service/edit").param("id", "45"))
				.andExpect(view().name("service/editService"))
				.andExpect(model().attribute("editService", service))
				.andExpect(model().attribute("employees", users))
				.andExpect(status().isOk());
		verify(dataService, times(1)).getServiceById(45L);
		verify(userService, times(1)).employeesList();
	}
	
	@Test
	public void test_update_service() throws Exception{
		mockMvc.perform(post("/service/edit").param("status", "done"))
				.andExpect(redirectedUrl("/service"))
				.andExpect(status().is3xxRedirection());
		verify(dataService, times(1)).getTodayDate();
		verify(dataService, times(1)).updateService(any(Service.class));
	}
	
	@Test
	public void test_update_service_if_service_not_done() throws Exception{
		mockMvc.perform(post("/service/edit").param("status", "notDone"))
				.andExpect(redirectedUrl("/service"))
				.andExpect(status().is3xxRedirection());
		verify(dataService, times(0)).getTodayDate();
		verify(dataService, times(1)).updateService(any());
	}
	
	@Test
	public void test_delete_service() throws Exception{
		mockMvc.perform(get("/service/delete").param("id", "45"))
				.andExpect(redirectedUrl("/service"))
				.andExpect(status().is3xxRedirection());
		verify(dataService, times(1)).deleteService(45L);;
	}
	
	@Test
	public void test_handle_error(){
		Service service = new Service();
		service.setServiceId(34L);
		ServiceNotFoundException exception = mock(ServiceNotFoundException.class);
		Model model = mock(Model.class);
		when(exception.getServiceId()).thenReturn(service.getServiceId());
		String result = serviceController.handleError(model, exception);
		assertEquals("service/serviceNotFound", result);
	}
	
	private Service getService(){
		Service service = new Service();
		service.setAccessories("power adapter");
		service.setAdder("jblack");
		service.setAddress("Wall Street 43/2");
		service.setContact("987-187-894");
		service.setDamage("power socket");
		service.setDamage("power adapter not charging");
		service.setEmployee("John Walles");
		service.setFinishDate(new Date(2016-02-15));
		service.setOwner("Adam Brick");
		service.setPrice("450");
		service.setProduct("Notebook Lenovo");
		service.setRecommendations("replace damaged part");
		service.setSellDate(new Date(2015-01-5));
		service.setSerialNumber("191CQS1298");
		service.setService("replaced power socket");
		service.setServiceId(45L);
		service.setStartDate(new Date(2016-02-02));
		service.setStatus("Done");
		service.setWarranty("Out Of Warranty");
		
		return service;
	}
	
	private User getUser(){
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
