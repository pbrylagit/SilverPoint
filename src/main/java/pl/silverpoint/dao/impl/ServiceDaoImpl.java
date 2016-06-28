package pl.silverpoint.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import pl.silverpoint.dao.ServiceDao;
import pl.silverpoint.domain.Service;
import pl.silverpoint.exception.ServiceNotFoundException;

public class ServiceDaoImpl implements ServiceDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Service> list() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Service> serviceList = session.createQuery("from Service").list();
		session.close();
		return serviceList;
	}

	@Override
	public void addService(Service newService) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(newService);
		transaction.commit();
		session.close();
	}

	@Override
	public Service getServiceById(long serviceId) {
		Service service;
		Session session = sessionFactory.openSession();
		try {
			service = (Service) session.get(Service.class, serviceId);
			if(service == null){
				throw new ServiceNotFoundException(serviceId);
			}
		} catch (HibernateException e) {
			throw new ServiceNotFoundException(serviceId);
		}
		session.close();
		return service;
	}

	@Override
	public void updateService(Service editedService) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(editedService);
		transaction.commit();
		session.close();
	}

	@Override
	public void deleteService(long serviceId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Service service = (Service) session.load(Service.class, serviceId);
		session.delete(service);
		transaction.commit();
		session.close();
	}

}
