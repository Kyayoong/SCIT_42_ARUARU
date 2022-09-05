package com.restaurantreservation.aruaru.service;

import java.util.UUID;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import com.restaurantreservation.aruaru.common.BaseException;
import com.restaurantreservation.aruaru.dao.MailDao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailService {
	
	public JavaMailSender javaMailSender;
	MailDao MailDao;
	
	private MimeMessage createMessage(String code, String email) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();

		message.addRecipients(Message.RecipientType.TO, email);
		message.setSubject("ARUARU 회원 가입 인증 번호입니다.");
		message.setText("이메일 인증코드: " + code);

		message.setFrom(new InternetAddress("aehongzzang@naver.com"));

		return message;
	}

	public void sendMail(String code, String email) throws Exception {
		try {
			MimeMessage mimeMessage = createMessage(code, email);
			javaMailSender.send(mimeMessage);
		} catch (MailException mailException) {
			mailException.printStackTrace();
			throw new IllegalAccessException();
		}
	}

	public long sendCertificationMail(String email) throws BaseException {
		try {
			String code = UUID.randomUUID().toString().substring(0, 6);
			sendMail(code, email);

			return MailDao.createVerificationCode(code, email);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BaseException("DB 연결에 문제가 있습니다.");
		}
	}
}
