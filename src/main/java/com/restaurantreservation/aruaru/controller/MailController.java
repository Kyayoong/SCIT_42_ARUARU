package com.restaurantreservation.aruaru.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurantreservation.aruaru.common.BaseException;
import com.restaurantreservation.aruaru.service.MailService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailController {

	MailService mailService;

	@ResponseBody
	@PostMapping("/api/skdjf")
	public ResponseEntity<Long> sendEmail(@RequestBody String data) throws BaseException {
		JSONObject parser = new JSONObject(data);
		String email = parser.getString("email");

		mailService.sendCertificationMail(email);
		long verifyCodeId = mailService.sendCertificationMail(email);
		return ResponseEntity.ok(verifyCodeId);

	}
}
