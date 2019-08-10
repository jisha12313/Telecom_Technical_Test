package com.exercise.telecom.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.telecom.dto.ResponseStatusDto;
import com.exercise.telecom.dto.TelephoneDto;
import com.exercise.telecom.entity.Telephone;
import com.exercise.telecom.exception.ResourceNotFoundException;
import com.exercise.telecom.repository.CustomerRepository;
import com.exercise.telecom.repository.TelePhoneRepository;
import com.exercise.telecom.util.PhoneStatusEnum;
import com.exercise.telecom.util.RestErrorAndMessages;
import com.exercise.telecom.util.Utility;

/**
 * Business Implementation Class for Telecom Service
 * 
 * @author jisha
 */

@Service
public class TelecomServiceImpl implements TelecomService, RestErrorAndMessages {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TelecomServiceImpl.class);

	public static final String DE_ACTIVE = "DEACTIVE";

	@Autowired
	TelePhoneRepository telePhoneRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	private DozerBeanMapper mapper;

	/**
	 * Method to get all phone numbers allocated to a particular customer
	 * 
	 * @param customerId
	 * @return List of TelephoneDtos
	 * 
	 */
	@Override
	public List<TelephoneDto> getAllPhoneNumbers(Integer customerId) {
		Collection<Telephone> telephones = telePhoneRepository.findByCustomerCustomerId(customerId);
		List<TelephoneDto> telephoneDtos = new ArrayList<>();
		telephoneDtos = telephones.stream().map(telephone -> mapper.map(telephone, TelephoneDto.class))
				.collect(Collectors.toList());
		return telephoneDtos;

	}

	/**
	 * Method to get all phone numbers
	 * 
	 * @return List of TelephoneDtos
	 */
	public List<TelephoneDto> getAllPhoneNumbers() {
		Collection<Telephone> telephones = telePhoneRepository.findAll();
		List<TelephoneDto> telephoneDtos = new ArrayList<>();
		telephoneDtos = telephones.stream().map(telephone -> mapper.map(telephone, TelephoneDto.class))
				.collect(Collectors.toList());
		return telephoneDtos;

	}

	/**
	 * Method to activate a phone for particular customer
	 * 
	 * @param customerId
	 * @param phoneNumber
	 * @param status
	 * @return ResponseStatusDto
	 */
	public ResponseStatusDto changeStatus(Integer customerId, String phoneNumber, PhoneStatusEnum status) {
		ResponseStatusDto responseStatusDto = null;
		LOGGER.info("inside service for change status");
		if (!customerRepository.existsById(customerId)) {
			throw new ResourceNotFoundException("customerId " + customerId + " not found");
		}
		Telephone telephone = telePhoneRepository.findTelephoneByTelePhoneNumberAndCustomerCustomerId(phoneNumber,
				customerId);
		if (telephone != null) {
			if (!status.getStatus().equals(telephone.getStatusCode())) {
				return updateTelephoneStatus(telephone,status);
			} else {
				responseStatusDto = createStatusUpdateErrorResponseStatus(status);
			}
		} else {
			responseStatusDto = Utility.createResponseStatus(PHONE_NOT_AVAILABLE[0], PHONE_NOT_AVAILABLE[1],
					PHONE_NOT_AVAILABLE[2]);
		}
		LOGGER.info("responseStatusDto message" + responseStatusDto.getMessage());
		return responseStatusDto;
	}

	/**
	 * Method to update the status of Telephone
	 * 
	 * @param telephone
	 * @param status
	 * @return
	 */
	private ResponseStatusDto updateTelephoneStatus(Telephone telephone, PhoneStatusEnum status) {
		telephone.setStatusCode(PhoneStatusEnum.STATUS_ACTIVE.getStatus());
		telePhoneRepository.save(telephone);
		return Utility.createResponseStatus(SUCCESS[0], SUCCESS[1], SUCCESS[2]);
	}
	
	/**
	 * Method to return the error response for telephone status update
	 * 
	 * @param status
	 * @return
	 */
	private ResponseStatusDto createStatusUpdateErrorResponseStatus(PhoneStatusEnum status) {
		if(PhoneStatusEnum.STATUS_ACTIVE.equals(status)) {
			return Utility.createResponseStatus(PHONE_IS_ALREADY_ACTIVE[0],
					PHONE_IS_ALREADY_ACTIVE[1], PHONE_IS_ALREADY_ACTIVE[2]);
		} else if(PhoneStatusEnum.STATUS_DEACTIVE.equals(status)) {
			return Utility.createResponseStatus(PHONE_IS_ALREADY_DEACTIVE[0],
					PHONE_IS_ALREADY_DEACTIVE[1], PHONE_IS_ALREADY_DEACTIVE[2]);
		}
		return null;
	}

	

}
