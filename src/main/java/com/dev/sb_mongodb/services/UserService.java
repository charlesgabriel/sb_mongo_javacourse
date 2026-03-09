package com.dev.sb_mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sb_mongodb.domain.User;
import com.dev.sb_mongodb.repository.UserRepository;
import com.dev.sb_mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;

	public List<User> findAll() {
		return uRepo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = uRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found!"));
	}
}
