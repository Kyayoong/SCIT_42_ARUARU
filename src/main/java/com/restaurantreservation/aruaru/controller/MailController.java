package com.restaurantreservation.aruaru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurantreservation.aruaru.service.MailSenderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MailController {
   
   @Autowired
   MailSenderService mailSenderService;
   
   @GetMapping("login/mailConfirm")
   public String mailConfirm() {
      return "registView/mailConfirm";
   }
   
   
   @PostMapping("login/mailConfirm")
   @ResponseBody
   public String mailSend(String email,String domain) throws Exception{
      
      mailSenderService.mailSend(email, domain);
      
      log.debug(email,domain);
      
      return "mailConfirm";
   }
}