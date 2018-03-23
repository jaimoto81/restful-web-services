package com.jaimoto.rest.webservices.restfulwebservices.user;


import com.jaimoto.rest.webservices.restfulwebservices.user.vo.User;
import com.sun.javaws.security.Resource;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


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
	public org.springframework.hateoas.Resource<User> getUser(@PathVariable int id){
		User user =   userDaoService.findOne(id);
		if( user == null){
			throw new UserNotFoundException("User not found "+id);
		}

		//add the link for retrieve all the users
		org.springframework.hateoas.Resource<User> res
			= new org.springframework.hateoas.Resource<User>(user);

		ControllerLinkBuilder link =
		linkTo(methodOn(this.getClass()).getUsers());

		res.add(link.withRel("all-users	"));
		//return user;
		return res;
	}

	//retrieve User

	/**
	 *
	 * @param usr
	 */
	@PostMapping("/users")
	public ResponseEntity createUser(@Valid @RequestBody User usr){
		User newUser = userDaoService.save(usr);
		//return newUser;
		URI location = ServletUriComponentsBuilder.
			fromCurrentRequest().
			path("/{id}").
			buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		int result =  userDaoService.deleteUser(id);
		if( result == -1){
			throw new UserNotFoundException("User not found "+id);
		}
		//TODO Respuesta OK de borrado.
	}

	@PostMapping("/users/{id_usr}/posts/")
	public ResponseEntity createUserPost(@RequestBody User usr){
		User newUser = userDaoService.save(usr);
		//return newUser;
		URI location = ServletUriComponentsBuilder.
			fromCurrentRequest().
			path("/{id}").
			buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
