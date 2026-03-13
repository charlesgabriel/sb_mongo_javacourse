package com.dev.sb_mongodb.services;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.sb_mongodb.domain.Post;
import com.dev.sb_mongodb.repository.PostRepository;
import com.dev.sb_mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found!"));
	}

	public List<Post> findByTitle(String text) {
		// return repository.findByTitleContainingIgnoreCase(text);
		return repository.searchTitle(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		// maxDate = new Date(maxDate.getTime() + 23 * 59 * 59 * 999);
		Duration duration = Duration.ofHours(23);
		maxDate = new Date(maxDate.getTime() + duration.toMillis());
		return repository.fullSearch(text, minDate, maxDate);
	}
}
