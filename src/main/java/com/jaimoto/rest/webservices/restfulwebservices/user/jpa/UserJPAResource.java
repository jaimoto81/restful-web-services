package com.jaimoto.rest.webservices.restfulwebservices.user.jpa;


import com.jaimoto.rest.webservices.restfulwebservices.user.UserDaoService;
import com.jaimoto.rest.webservices.restfulwebservices.user.UserNotFoundException;
import com.jaimoto.rest.webservices.restfulwebservices.user.vo.Post;
import com.jaimoto.rest.webservices.restfulwebservices.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class UserJPAResource {

	@Autowired
	private UserDaoService userDaoService;

	@Autowired
	private UserRepository userRepository;


    @Autowired
    private PostRepository postRepository;

	//retrieve all users
	@GetMapping("/jpa/users")
	public List<User> getUsers(){
		return  userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public org.springframework.hateoas.Resource<User> getUser(@PathVariable int id){
		Optional<User> user =   userRepository.findById(id);
		if( !user.isPresent()){
			throw new UserNotFoundException("User not found "+id);
		}

		//add the link for retrieve all the users
		org.springframework.hateoas.Resource<User> res
			= new org.springframework.hateoas.Resource<User>(user.get());

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
	@PostMapping("/jpa/users")
	public ResponseEntity createUser(@Valid @RequestBody User usr){
		User newUser = userRepository.save(usr);
		//return newUser;
		URI location = ServletUriComponentsBuilder.
			fromCurrentRequest().
			path("/{id}").
			buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);

		//TODO Respuesta OK de borrado.
	}

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id){
        Optional<User> user =   userRepository.findById(id);
        if( !user.isPresent()){
            throw new UserNotFoundException("User not found "+id);
        }

        //add the link for retrieve all the users
        //org.springframework.hateoas.Resource<List <Post>> res
        //        = new org.springframework.hateoas.Resource<List<Post>>(user.get().getPosts());

        //ControllerLinkBuilder link =
         //       linkTo(methodOn(this.getClass()).getUsers());

        //res.add(link.withRel("all-users	"));
        //return user;
        //return res;
        return user.get().getPosts();

    }

    /**
     *
     * @param post
     */
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> userOptional =   userRepository.findById(id);
        if( !userOptional.isPresent()){
            throw new UserNotFoundException("User not found "+id);
        }

        User user = userOptional.get();

        post.setUser(user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();



    }
}
