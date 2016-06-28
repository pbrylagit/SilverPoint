package pl.silverpoint.dao.impl;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
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

import pl.silverpoint.domain.Service;
import pl.silverpoint.exception.ServiceNotFoundException;

public class ServiceDaoImplTest {

	@Mock
	private SessionFactory mockSessionFactory;
	
	@Mock
	private Session mockSession;
	
	@Mock
	private Transaction mockTransaction;
	
	@InjectMocks
	private ServiceDaoImpl serviceDao;
	
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
		List<Service> serviceList = new ArrayList<>();
		serviceList.add(getService());
		Query mockQuery = mock(Query.class);
		when(mockSession.createQuery(anyString())).thenReturn(mockQuery);
		when(mockQuery.list()).thenReturn(serviceList);
		assertEquals(serviceList, serviceDao.list());
		verify(mockSession.createQuery(anyString()), times(1)).list();
	}

	@Test
	public void test_add_service(){
		Service service = getService();
		when(mockSession.beginTransaction()).thenReturn(mockTransaction);
		serviceDao.addService(service);
		verify(mockSession, times(1)).save(service);
		verify(mockTransaction, times(1)).commit();
	}
	
	@Test
	public void test_get_service_by_id(){
		Service service = getService();
		when(mockSession.get(Service.class, 45L)).thenReturn(service);
		assertEquals(service, serviceDao.getServiceById(45L));
		verify(mockSession, times(1)).get(Service.class, 45L);
	}
	
	@Test(expected = ServiceNotFoundException.class)
	public void test_get_service_by_id_should_throw_service_not_found_exception(){
		when(mockSession.get(Service.class, 23L)).thenReturn(null);
		assertEquals(null, serviceDao.getServiceById(23L));
		verify(mockSession, times(1)).get(Service.class, 23L);
	}
	
	@Test
	public void test_update_service(){
		Service service = getService();
		when(mockSession.beginTransaction()).thenReturn(mockTransaction);
		serviceDao.updateService(service);
		verify(mockSession, times(1)).update(service);
		verify(mockTransaction, times(1)).commit();
	}
	
	@Test
	public void test_delete_service(){
		Service service = getService();
		when(mockSession.beginTransaction()).thenReturn(mockTransaction);
		when(mockSession.load(Service.class, 45L)).thenReturn(service);
		serviceDao.deleteService(45L);
		verify(mockSession, times(1)).delete(service);
		verify(mockTransaction, times(1)).commit();
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
}
