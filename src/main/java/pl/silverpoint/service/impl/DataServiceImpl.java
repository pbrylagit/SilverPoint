package pl.silverpoint.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.silverpoint.dao.ServiceDao;
import pl.silverpoint.domain.Service;
import pl.silverpoint.service.DataService;

@org.springframework.stereotype.Service
public class DataServiceImpl implements DataService{

	@Autowired
	private ServiceDao serviceDao;
	
	@Override
	public List<Service> list() {
		return serviceDao.list();
	}

	@Override
	public void addService(Service newService) {
		serviceDao.addService(newService);
	}

	@Override
	public Date getTodayDate() {
		Date todayDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formatterDate = formatter.format(todayDate);
		try {
			todayDate = formatter.parse(formatterDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return todayDate;
	}

	@Override
	public Service getServiceById(long serviceId) {
		return serviceDao.getServiceById(serviceId);
	}

	@Override
	public void updateService(Service editedService) {
		serviceDao.updateService(editedService);
	}

	@Override
	public void deleteService(long serviceId) {
		serviceDao.deleteService(serviceId);
	}

}
