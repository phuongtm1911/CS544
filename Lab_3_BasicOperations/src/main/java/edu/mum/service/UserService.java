package edu.mum.service;

import java.util.List;

import edu.mum.domain.User;
 
public interface UserService {

	public void save(User user);
	public List<User> findAll();
	public User findByEmail(String email);

	public User update(User user);
	public void detach(User user);

	public void refresh(User user, String email);
	public void flush(User user, String email);
}
