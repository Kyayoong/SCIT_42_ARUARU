package com.restaurantreservation.aruaru.dao;

public interface MailDao {

	public long createVerificationCode(String code, String email);

}
