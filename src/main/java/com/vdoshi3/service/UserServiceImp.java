package com.vdoshi3.service;

import java.util.List;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.vdoshi3.dao.UserDao;
import com.vdoshi3.entity.LoginResponse;
import com.vdoshi3.entity.User;
import com.vdoshi3.exception.InvalidCredentialsException;
import com.vdoshi3.exception.ResourceAlreadyExistsException;
import com.vdoshi3.exception.ResourceNotFoundException;
import com.vdoshi3.utils.DefaultProfilePic;
import com.vdoshi3.utils.JwtToken;

@Service
@Transactional
@PropertySource("classpath:app.properties")
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao repo;
	@Autowired
	private JwtToken jwt;
	@Autowired
	private Environment env;
	@Autowired
	private DefaultProfilePic dp;

	@Override
	public User create(User user) throws ResourceAlreadyExistsException {
		if (repo.findByEmail(user.getEmail()) != null || repo.findById(user.getUid()) != null) {
			throw new ResourceAlreadyExistsException();
		} else {
			//Create Salt
			String salt = BCrypt.gensalt();
			user.setSalt(salt);
			
			//Encrypt Password
			String encryptedPassword = BCrypt.hashpw(user.getUserpassword(), BCrypt.gensalt());
			user.setEncryptedPassword(encryptedPassword);
			
			//Add role to 'user'
			user.setRole("user");
			
			//Add a random profile pic if missing
			if(user.getProfilePic() == null){}
				user.setProfilePic(dp.getRandomPicture());
			//Create User
			return repo.create(user);
		}
	}

	@Override
	public List<User> findAll() {
		return repo.findAll();
	}

	@Override
	public User findById(String uid) throws ResourceNotFoundException {
		User u = repo.findById(uid);
		if (u != null) {
			return u;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public User findByEmail(String email) throws ResourceNotFoundException {
		User u = repo.findByEmail(email);
		if (u != null) {
			return u;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public User update(String uid, User user) throws ResourceNotFoundException {
		User u = repo.findById(uid);
		if (u != null) {
			return repo.update(user);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public void delete(String uid) throws ResourceNotFoundException {
		User u = repo.findById(uid);
		if (u != null) {
			repo.delete(u);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public LoginResponse login(User user) throws ResourceNotFoundException, InvalidCredentialsException {
		User u = repo.findByEmail(user.getEmail());
		if (u != null) {
			if (BCrypt.checkpw(user.getUserpassword(), u.getEncryptedPassword())) {
				System.out.println("It matches");
				String jwttoken =  jwt.createJWT(u.getUid(),u.getEmail(), u.getRole(), Long.parseLong(env.getProperty("jwt.ttl")));
				System.out.println("JWT: "+jwttoken);
				
				LoginResponse loginresponse= new LoginResponse();
				loginresponse.setToken("Bearer "+jwttoken);
				loginresponse.setUid(u.getUid());
				return loginresponse;
			} else {
				System.out.println("It does not match");
				throw new InvalidCredentialsException();
			}
		} else {
			throw new ResourceNotFoundException();
		}
	}

}
