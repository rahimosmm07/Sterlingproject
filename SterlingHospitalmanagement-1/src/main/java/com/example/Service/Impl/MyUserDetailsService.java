package com.example.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Entity.User;
import com.example.Exception.UserNotFoundException;
import com.example.Repository.UserLoginRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserLoginRepository userLoginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // TODO Auto-generated
																								// method stub
		User user = userLoginRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new UserPrincipal(user);
	}

	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userLoginRepository.save(user);
	}

	public User updateUser(User user) throws UserNotFoundException {
		// TODO Auto-generated method stub

		Optional<User> optional = userLoginRepository.findById((int) user.getUserid());

		if (optional.isPresent()) {
			User _user = userLoginRepository.findById((int) user.getUserid()).get();
			_user.setUsername(user.getUsername());
			_user.setPassword(user.getPassword());
			_user.setRole(user.getRole());

			return userLoginRepository.save(_user);
		} else {
			throw new UserNotFoundException("User not found with the matching ID!");
		}

		// return optional.get();
	}

//	@Override
//	public void deleteDoctor(int id) throws DoctorNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Doctor> d = doctorRepository.findById(id);
//
//		if (d.isPresent()) {
//			doctorRepository.deleteById(id);
//		} else {
//			throw new DoctorNotFoundException("Doctor not found with the matching ID!");
//		}

}
