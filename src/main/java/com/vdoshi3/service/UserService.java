package com.vdoshi3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.vdoshi3.database.UserDatabase;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.UserAlreadyExistsException;
import com.vdoshi3.exception.UserNotFoundException;

@Service
public class UserService implements IUserService{
	static Map<String, User> users = (ConcurrentHashMap<String, User>)UserDatabase.getUsers();
	
	public UserService() {
		User u1 = new User();
		u1.setUid(1);
		u1.setUserid("rocky101");
		u1.setUname("Rocky");
		u1.setUpassword("Balboa");
		u1.setEmail("rocky101@boxing.com");
		User u2 = new User();
		u2.setUid(1);
		u2.setUserid("shocky101");
		u2.setUname("Shocky");
		u2.setUpassword("Shreiff");
		u2.setEmail("shocky101@boxing.com");
		users.put(u1.getUserid(), u1);
		users.put(u2.getUserid(), u2);

	}
	
	@Override
	public User create(User user) throws UserAlreadyExistsException {
		if(users.containsKey(user.getUserid())){
			throw new UserAlreadyExistsException();
		}
		else{
			users.put(user.getUserid(), user);
			return user;
		}
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<User>(users.values());
	}

	@Override
	public User find(String userid) throws UserNotFoundException{
		if(users.containsKey(userid)){
			return users.get(userid);
		}else{
			throw new UserNotFoundException();
		}
	}

	@Override
	public User update(String userid, User user) throws UserNotFoundException {
		if(users.containsKey(userid)){
			users.put(userid, user);
			return user;
		}else{
			throw new UserNotFoundException();
		}
	}

	@Override
	public User delete(String userid) throws UserNotFoundException {
		if(users.containsKey(userid)){
			User user = users.get(userid);
			users.remove(userid);
			return user;
		}else{
			throw new UserNotFoundException();
		}
	}

}
