package com.Learning.EmailSender.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Learning.EmailSender.helper.CustomResponse;
import com.Learning.EmailSender.helper.EmailRequest;
import com.Learning.EmailSender.service.EmailService;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;

	@PostMapping("/send") 
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		emailService.sendEmailInHtml(request.getTo(),request.getSubject(),request.getMessage());
		return ResponseEntity.ok(
				CustomResponse.builder().message("Email send successfully  !! ").httpStatus(HttpStatus.OK).success(true).build()
				);
	}
	
	@PostMapping("/send-with-file")
	public ResponseEntity<CustomResponse> sendWithFile(@RequestPart EmailRequest request , @RequestPart MultipartFile file) throws IOException{
		
		emailService.sendEmailWithFile(request.getTo(), request.getSubject(), request.getMessage(), file.getInputStream());
		return ResponseEntity.ok(
				CustomResponse.builder().message("Email send successfully  !! ").httpStatus(HttpStatus.OK).success(true).build());
	}
}
