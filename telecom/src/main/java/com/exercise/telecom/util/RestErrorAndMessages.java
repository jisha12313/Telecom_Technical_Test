package com.exercise.telecom.util;

public interface RestErrorAndMessages {
	static String[] SUCCESS = { "-1", null, "The phone is successfully activated for the customer" };
	static String[] PHONE_IS_ALREADY_ACTIVE = { "-1", "10042", "The Phone is already in active status" };
	static String[] PHONE_IS_ALREADY_DEACTIVE = { "-1", "10042", "The Phone is already in deactive status" };
	static String[] PHONE_NOT_AVAILABLE = { "-1", "10043", "Unable to find the phone number is system" };
	static String[] UPDATE_PHONE_STATUS_UNKNOWN_ERROR = { "-1", "10044", "Unknown Error while activating the phone" };

}