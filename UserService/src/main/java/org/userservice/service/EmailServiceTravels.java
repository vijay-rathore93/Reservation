package org.userservice.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmailServiceTravels {
	
	
	@Value("${MAIL_SERVICE_TRAVELS}")
	private String mailId;

	private final JavaMailSender javaMailSender;

	
	@Async
	public void sendMail(HttpServletRequest htsr, String token) {
		String url = htsr.getScheme() + "://" + htsr.getServerName() + ":" + htsr.getServerPort();
		System.out.println(url);
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(mailId);
		smm.setSubject("Verification Mail");
		
		smm.setText(url + "/confirmTravels?token=" + token);
		
		javaMailSender.send(smm);

	}

}


