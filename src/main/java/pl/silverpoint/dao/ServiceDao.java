package pl.silverpoint.dao;

import java.util.List;

import pl.silverpoint.domain.Service;

public interface ServiceDao {
	
	List<Service> list();
	
	void addService(Service newService);
	
	Service getServiceById(long serviceId);

	void updateService(Service editedService);

	void deleteService(long serviceId);

}
