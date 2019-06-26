package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.UserDao;
import edu.mum.domain.User;

@Service
@Transactional 
public class UserServiceImpl implements edu.mum.service.UserService {
	
	@Autowired
	private UserDao userDao;

	public void save( User user) { userDao.save(user); }

 	public User update (User user) { return userDao.update(user); }

	public void detach (User user) { userDao.detach(user); }

	public void refresh(User user, String email) {
     	userDao.save(user);
     	user.setEmail(email);
		userDao.refresh(user);
		System.out.println("Email from DB: " + user.getEmail());
	}

	public void flush(User user, String email) {
		userDao.save(user);
		userDao.flush();
		System.out.println("Updated email: " + findByEmail(email).getEmail());
	}

	public List<User> findAll() {
		return (List<User>)userDao.findAll();
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
 

}
