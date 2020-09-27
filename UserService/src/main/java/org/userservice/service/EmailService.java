package org.userservice.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {


	private final JavaMailSender javaMailSender;

	
	
	@Async
	public void sendMail(String emailId, HttpServletRequest htsr, String token) {
		String url = htsr.getScheme() + "://" + htsr.getServerName() + ":" + htsr.getServerPort();
		System.out.println(url);
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(emailId);
		smm.setSubject("Verification Mail");

		smm.setText(url + "/confirm?token=" + token);

		javaMailSender.send(smm);

	}

}
