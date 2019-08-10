package com.exercise.telecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exercise.telecom.entity.Telephone;

@Repository
public interface TelePhoneRepository extends JpaRepository<Telephone, Integer> {

	/**
	 * Method to find Telephone details of a particular customer
	 * @param customerId
	 * @return Telephone List
	 */
	List<Telephone> findByCustomerCustomerId(Integer customerId);

	/**
	 * Method to find the telephone details for a particular customer and phone number
	 * @param phoneNumber
	 * @param customerId
	 * @return Telephone details
	 */
	Telephone findTelephoneByTelePhoneNumberAndCustomerCustomerId(String phoneNumber, Integer customerId);

}
