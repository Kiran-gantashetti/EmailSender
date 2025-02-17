package com.Learning.EmailSender.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.Learning.EmailSender.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("selmilton@gmail.com");

		mailSender.send(simpleMailMessage);
		logger.info("Email has be sent to the person......");
	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
       
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setText(message);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setFrom("selmilton2@gmail.com");
		
		mailSender.send(simpleMailMessage);
		logger.info("Mail has been sent to the entire batch");
	}

	@Override
	public void sendEmailInHtml(String to, String Subject, String htmlContent) {
		
		MimeMessage simpleMailMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true , "UTF-8");
			helper.setTo(to);
			helper.setSubject(Subject);
			helper.setText(htmlContent,true);
			helper.setFrom("selmilton2@gmail.com");
			
			mailSender.send(simpleMailMessage);
			logger.info("The html content was shared via email!!!!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, InputStream is) {
       
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage , true);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(message , true);
			
			File file = File.createTempFile("attachment",".png");
			Files.copy(is , file.toPath() , StandardCopyOption.REPLACE_EXISTING);
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mimeMessage);
			logger.info("This mail contains a file");
			
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
	}

}
