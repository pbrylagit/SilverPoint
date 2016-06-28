package pl.silverpoint.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import pl.silverpoint.dao.UserDao;
import pl.silverpoint.domain.User;
import pl.silverpoint.exception.UserNotFoundException;

public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> list() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<User> userList = session.createQuery("from User").list();
		session.close();
		return userList;
	}
	
	@Override
	public List<User> employeesList() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<User> employeesList = session.createQuery("from User U where U.permission like 'ROLE_ADMIN' or U.permission like 'ROLE_USER'").list();
		session.close();
		return employeesList;
	}

	@Override
	public void addUser(User newUser) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(newUser);
		transaction.commit();
		session.close();
	}

	@Override
	public User getUserById(int userId) throws UserNotFoundException{
		User user;
		Session session = sessionFactory.openSession();
		try {
			user = (User) session.get(User.class, userId);
			if(user== null){
				throw new UserNotFoundException(userId);
			}
		} catch (HibernateException e) {
			throw new UserNotFoundException(userId);
		}
		return user;
	}

	@Override
	public void updateUser(User editedUser){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(editedUser);
		transaction.commit();
		session.close();
	}
	
	@Override
	public void deleteUser(int userId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		User user = (User) session.load(User.class, userId);
		session.delete(user);
		transaction.commit();
		session.close();
	}
	
	@Override
	public List<User> checkIfEmailExist(String email) throws HibernateException{
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User U where U.email =:email");
		query.setParameter("email", email);
		@SuppressWarnings("unchecked")
		List<User> user = query.list();
		session.close();
		return user;
	}
	
	@Override
	public List<User> checkIfUsernameExist(String username) throws HibernateException{
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User U where U.username =:username");
		query.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<User> user = query.list();
		session.close();
		return user;
	}
}
