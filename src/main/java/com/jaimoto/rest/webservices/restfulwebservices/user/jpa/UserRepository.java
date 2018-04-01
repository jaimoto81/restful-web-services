package com.jaimoto.rest.webservices.restfulwebservices.user.jpa;

import com.jaimoto.rest.webservices.restfulwebservices.user.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
}
