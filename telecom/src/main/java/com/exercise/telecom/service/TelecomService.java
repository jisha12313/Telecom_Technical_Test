package com.exercise.telecom.service;

import java.util.List;

import com.exercise.telecom.dto.ResponseStatusDto;
import com.exercise.telecom.dto.TelephoneDto;
import com.exercise.telecom.util.PhoneStatusEnum;

/**
 * The interface class for Telecom service
 * 
 * @author jisha
 */
public interface TelecomService {

	/**
	 * Method to get all phone numbers of a particular customer
	 * 
	 * @param customerId
	 * @return List of Telephone DTOs
	 */
	public List<TelephoneDto> getAllPhoneNumbers(Integer customerId);

	/**
	 * Method to get all phone numbers
	 * 
	 * @return List of Telephone DTOs
	 * 
	 */

	public List<TelephoneDto> getAllPhoneNumbers();

	/**
	 * Method to activate a phone for particular customer
	 * 
	 * @param customerId
	 * @param phoneNumber
	 * @param status
	 * @return ResponseStatusDto
	 * 
	 */
	public ResponseStatusDto changeStatus(Integer customerId, String phoneNumber, PhoneStatusEnum status);

}
