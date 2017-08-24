/**
 * 
 */
package com.aequalis.youtube.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aequalis.youtube.model.User;
import com.aequalis.youtube.repository.UserRepository;
import com.aequalis.youtube.service.UserService;

/**
 * @author anand
 *
 */
@Service
@Qualifier("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void addUser(User user) {
		userRepository.saveAndFlush(user);
	}

	@Override
	public User loginUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public User findByUserid(Long userid) {
		return userRepository.findByUserid(userid);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}


	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findByIsadminuser(boolean isadminuser) {
		return userRepository.findByIsadminuser(isadminuser);
	}

}
