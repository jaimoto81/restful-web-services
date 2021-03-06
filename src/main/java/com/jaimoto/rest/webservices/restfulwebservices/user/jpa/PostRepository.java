package com.jaimoto.rest.webservices.restfulwebservices.user.jpa;

import com.jaimoto.rest.webservices.restfulwebservices.user.vo.Post;
import com.jaimoto.rest.webservices.restfulwebservices.user.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>{
}
