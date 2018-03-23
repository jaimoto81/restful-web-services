package com.jaimoto.rest.webservices.restfulwebservices.user;


import com.jaimoto.rest.webservices.restfulwebservices.user.vo.Post;
import com.jaimoto.rest.webservices.restfulwebservices.user.vo.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;

	static{
		users.add(new User(1,"Jaimoto",new Date()));
		users.add(new User(2,"Lulu",new Date()));
		users.add(new User(3,"Yoda",new Date()));
	}

	public User save(User user){
		if(user.getId() == null){
			user.setId(usersCount++);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id){
		for (User user: users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	public List<User> findAll(){
		return users;
	}

	/** EXERCISE STEP 13 **/
	public int deleteUser(int id){
		for (User user: users){
			if(user.getId() == id){
				users.remove(user);
				return id;
			}
		}
		return -1;
	}

	public Post createPost(int userId, String comment){
		User usr = findOne(userId);
		UUID uuid = UUID.randomUUID();
		Post post = new Post(uuid.version(),new Date(),comment,null);
		usr.getPosts().add(post);
		return post;
	}


}
