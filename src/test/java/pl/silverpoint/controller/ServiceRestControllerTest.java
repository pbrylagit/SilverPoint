package pl.silverpoint.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.silverpoint.domain.Service;
import pl.silverpoint.service.DataService;

public class ServiceRestControllerTest {

	@Mock
	private DataService mockDataService;
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private ServiceRestController serviceRestController;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(serviceRestController).build();
	}
	
	@Test
	public void test_read() throws Exception{
		Service service = getService();
		when(mockDataService.getServiceById(45L)).thenReturn(service);
		mockMvc.perform(get("/rest/service/45"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("contact").value("987-187-894"))
				.andExpect(jsonPath("description").value("power adapter not charging"))
				.andExpect(jsonPath("product").value("Notebook Lenovo"))
				.andExpect(jsonPath("serviceId").value(45));
	}
	
	private Service getService(){
		Service service = new Service();
		service.setAccessories("power adapter");
		service.setAdder("jblack");
		service.setAddress("Wall Street 43/2");
		service.setContact("987-187-894");
		service.setDamage("power socket");
		service.setDescription("power adapter not charging");
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
}
