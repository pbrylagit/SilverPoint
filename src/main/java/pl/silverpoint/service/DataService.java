package pl.silverpoint.service;

import java.util.Date;
import java.util.List;

import pl.silverpoint.domain.Service;

public interface DataService {

	List<Service> list();
	
	void addService(Service newService);

	Date getTodayDate();

	Service getServiceById(long serviceId);

	void updateService(Service editedService);

	void deleteService(long serviceId);

}
