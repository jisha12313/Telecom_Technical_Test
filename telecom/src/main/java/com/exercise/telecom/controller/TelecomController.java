package com.exercise.telecom.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.telecom.dto.ResponseStatusDto;
import com.exercise.telecom.dto.TelephoneDto;
import com.exercise.telecom.service.TelecomService;
import com.exercise.telecom.util.PhoneStatusEnum;

/**
 * A REST Controller class to define the Telecom API operations.
 * 
 * @author jisha
 *
 */
@RestController
@RequestMapping("/telecom")
public class TelecomController {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TelecomController.class);

	public static final Character ACTIVE = 'Y';

	@Autowired
	TelecomService telecomService;

	/**
	 * Method to get all phone numbers of a given customer
	 * 
	 * @param customerId
	 * @return List of Telephone numbers
	 */
	@GetMapping(value = "/{customerId}/getAllPhoneNumbersForCustomer")
	public List<TelephoneDto> getAllPhoneNumbersByCustomerId(@PathVariable final Integer customerId) {
		LOGGER.info("Get all telephone numbers :: getAllPhoneNumbersByCustomerId() :: Called");
		return telecomService.getAllPhoneNumbers(customerId);
	}

	/**
	 * Method to get all phone numbers
	 * 
	 * @return List of Telephone numbers
	 */

	@GetMapping("/getAllPhoneNumbers")
	public List<TelephoneDto> getAllPhoneNumbers() {
		return telecomService.getAllPhoneNumbers();
	}

	/**
	 * Activate phone number for a customer
	 * 
	 * @param customerId
	 * @param phoneNumber
	 */

	@PostMapping(value = "/{customerId}/activatePhone")
	public ResponseStatusDto activatePhone(@PathVariable final Integer customerId,
			@RequestBody TelephoneDto telephoneDto) {
		LOGGER.info("Activate the phone for customer :: activatePhone() :: Called" + telephoneDto.getPhoneNumber());
		ResponseStatusDto responseStatusDto = telecomService.changeStatus(customerId, telephoneDto.getPhoneNumber(),
				PhoneStatusEnum.STATUS_ACTIVE);
		if (responseStatusDto != null && (responseStatusDto.getMessage() != null)) {
			LOGGER.info("The Phone Activation status is  " + responseStatusDto.getMessage().toString());
		}
		return responseStatusDto;
	}

}
