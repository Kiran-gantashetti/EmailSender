package com.Learning.EmailSender;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Learning.EmailSender.service.EmailService;

@SpringBootTest(classes = EmailSenderApplication.class)
public class EmailSenderTest {
	@Autowired
	private EmailService emailService;
	
	@Test
	void emaiSendTest() {
		System.out.println("sending email");
		emailService.sendEmail("avigantasetti@gmail.com" , "Email from Springboot!!!" , "This email is sent using springboot while learning and to validate it I am send it to you.");
	}
	
	@Test
	void sendHtmlInEmail() {
		String html =""+ "<h1 style='color:red;border:1px solid red;'>Welcome to Learning about email via SpringBoot"+"";
		emailService.sendEmailInHtml("avigantasetti@gmail.com","Email from Springboot in html", html);
	}
//	@Test
//	void sendEmailWithFile() {
//		emailService.sendEmailWithFile("avigantasetti@gmail.com" , "Email contains a File as a content!!!" , "This mail contains file" , new File("C:\\Users\\KiranGantashetti\\Downloads\\scene.jpg"));
//	}
}
