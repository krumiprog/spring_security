package com.krumiprog.blog.repositories;

import com.krumiprog.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

}
