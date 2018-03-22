package com.jaimoto.rest.webservices.restfulwebservices.user;


import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	//retrieve all users
	@GetMapping("/users")
	public List<User> getUsers(){
		return  userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id){
		User user =   userDaoService.findOne(id);
		if( user == null){
			throw new UserNotFoundException("User not found "+id);
		}
		return user;
	}

	//retrieve User

	/**
	 *
	 * @param usr
	 */
	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody User usr){
		User newUser = userDaoService.save(usr);
		//return newUser;
		URI location = ServletUriComponentsBuilder.
			fromCurrentRequest().
			path("/{id}").
			buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public User getUser(@PathVariable int id){
		User user =   userDaoService.findOne(id);
		if( user == null){
			throw new UserNotFoundException("User not found "+id);
		}
		return user;
	}
}
