package pl.silverpoint.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

	private Service testedObject;
	
	@Before
	public void setup(){
		testedObject = new Service();
		testedObject.setAccessories("power adapter");
		testedObject.setAdder("tested adder");
		testedObject.setAddress("Wall Street");
		testedObject.setContact("123-456-789");
		testedObject.setDamage("damage power socket");
		testedObject.setDescription("notebook doesn't get up");
		testedObject.setEmployee("Jan Novak");
		testedObject.setFinishDate(new Date(20160508));
		testedObject.setOwner("Adam Tesla");
		testedObject.setPrice("450");
		testedObject.setProduct("notebook Vaio");
		testedObject.setRecommendations("change power socket");
		testedObject.setSellDate(new Date(20150402));
		testedObject.setSerialNumber("RV4T3E");
		testedObject.setService("replace power socket");
		testedObject.setServiceId(4L);
		testedObject.setStartDate(new Date(20160503));
		testedObject.setStatus("done");
		testedObject.setWarranty("warranty");
	}
	
	@Test
	public void testGetAccessories(){
		assertEquals("power adapter", testedObject.getAccessories());
	}
	
	@Test
	public void testGetAdder(){
		assertEquals("tested adder", testedObject.getAdder());
	}
	
	@Test
	public void testGetAddress(){
		assertEquals("Wall Street", testedObject.getAddress());
	}

	@Test
	public void testGetContact(){
		assertEquals("123-456-789", testedObject.getContact());
	}

	@Test
	public void testGetDamage(){
		assertEquals("damage power socket", testedObject.getDamage());
	}

	@Test
	public void testGetDescription(){
		assertEquals("notebook doesn't get up", testedObject.getDescription());
	}

	@Test
	public void testGetEmployee(){
		assertEquals("Jan Novak", testedObject.getEmployee());
	}

	@Test
	public void testGetFinishDate(){
		assertEquals(new Date(20160508), testedObject.getFinishDate());
	}

	@Test
	public void testGetOwner(){
		assertEquals("Adam Tesla", testedObject.getOwner());
	}

	@Test
	public void testGetPrice(){
		assertEquals("450", testedObject.getPrice());
	}

	@Test
	public void testGetProduct(){
		assertEquals("notebook Vaio", testedObject.getProduct());
	}

	@Test
	public void testGetRecommendations(){
		assertEquals("change power socket", testedObject.getRecommendations());
	}

	@Test
	public void testGetSellDate(){
		assertEquals(new Date(20150402), testedObject.getSellDate());
	}

	@Test
	public void testGetSerialNumber(){
		assertEquals("RV4T3E", testedObject.getSerialNumber());
	}

	@Test
	public void testGetService(){
		assertEquals("replace power socket", testedObject.getService());
	}

	@Test
	public void testGetServiceId(){
		assertEquals(new Long(4L), testedObject.getServiceId());
	}

	@Test
	public void testGetStartDate(){
		assertEquals(new Date(20160503), testedObject.getStartDate());
	}

	@Test
	public void testGetStatus(){
		assertEquals("done", testedObject.getStatus());
	}

	@Test
	public void testGetWarranty(){
		assertEquals("warranty", testedObject.getWarranty());
	}

}
