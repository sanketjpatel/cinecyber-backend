package com.vdoshi3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vdoshi3.controller.UserController;
import com.vdoshi3.entity.User;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		System.out.println(u1.toString());
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserController uc = context.getBean(UserController.class);
		System.out.println(uc.create(u1).toString());
		System.out.println(uc.create(u2).toString());
		System.out.println(uc.findAll().toString());
		context.close();
	}

}
