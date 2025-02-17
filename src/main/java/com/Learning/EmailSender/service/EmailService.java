package com.Learning.EmailSender.service;

import java.io.InputStream;


public interface EmailService {
	
	//send email to a single person 
	void sendEmail(String to , String subject , String message);
	//send email to multiple person
	void sendEmail(String []to , String subject , String message);
	//void sendEmailWithHtml
	void sendEmailInHtml(String to , String Subject , String htmlContent);
	//void send email with file 
	void sendEmailWithFile(String to , String subject , String message , InputStream file);
	

}
