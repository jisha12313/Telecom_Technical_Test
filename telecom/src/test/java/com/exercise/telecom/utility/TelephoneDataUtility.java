package com.exercise.telecom.utility;

import java.util.ArrayList;
import java.util.List;

import com.exercise.telecom.dto.CustomerDto;
import com.exercise.telecom.dto.TelephoneDto;
import com.exercise.telecom.entity.Customer;
import com.exercise.telecom.entity.Telephone;

public class TelephoneDataUtility {

	public static TelephoneDto getTelephoneDto_One() {
		TelephoneDto telephoneDto = new TelephoneDto();
		telephoneDto.setStatusCode("ACTV");
		telephoneDto.setActive('Y');
		telephoneDto.setCountryCode("+44");
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(1234);
		telephoneDto.setCustomerDto(customerDto);
		telephoneDto.setPhoneNumber("99999");
		return telephoneDto;
	}

	public static TelephoneDto getTelephoneDto_Two() {
		TelephoneDto telephoneDto = new TelephoneDto();
		telephoneDto.setStatusCode("INAC");
		telephoneDto.setActive('N');
		telephoneDto.setCountryCode("+91");
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(648483);
		telephoneDto.setCustomerDto(customerDto);
		telephoneDto.setPhoneNumber("52652627");
		return telephoneDto;
	}

	public static TelephoneDto getTelephoneDto_Three() {
		TelephoneDto telephoneDto = new TelephoneDto();
		telephoneDto.setStatusCode("ACTV");
		telephoneDto.setActive('Y');
		telephoneDto.setCountryCode("+1");
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(1234);
		telephoneDto.setCustomerDto(customerDto);
		telephoneDto.setPhoneNumber("64376982982");
		return telephoneDto;
	}

	public static TelephoneDto getTelephoneDto_Update() {
		TelephoneDto telephoneDto = new TelephoneDto();
		telephoneDto.setStatusCode("ACTV");
		telephoneDto.setActive('Y');
		telephoneDto.setCountryCode("+44");
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(1234);
		telephoneDto.setCustomerDto(customerDto);
		telephoneDto.setPhoneNumber("99999");
		return telephoneDto;
	}

	public static Customer getCustomer_one() {
		Customer customer = new Customer();
		customer.setCustomerId(1234);
		customer.setName("Sample1");
		return customer;
	}

	public static Telephone getTelephone_One() {
		Telephone telephone = new Telephone();
		telephone.setStatusCode("INAC");
		telephone.setCountryCode("+44");
		Customer customer = new Customer();
		customer.setCustomerId(1234);
		customer.setName("one");
		telephone.setCustomer(customer);
		telephone.setTelePhoneNumber("99999");
		return telephone;
	}
	
	public static Telephone getTelephone_Modifiy() {
		Telephone telephone = new Telephone();
		telephone.setStatusCode("ACTV");
		telephone.setCountryCode("+44");
		Customer customer = new Customer();
		customer.setCustomerId(1234);
		customer.setName("one");
		telephone.setCustomer(customer);
		telephone.setTelePhoneNumber("99999");
		return telephone;
	}

	public static Telephone getTelephone_Two() {
		Telephone telephone = new Telephone();
		telephone.setStatusCode("INAC");
		telephone.setCountryCode("+91");
		Customer customer = new Customer();
		customer.setCustomerId(648483);
		customer.setName("two");
		telephone.setCustomer(customer);
		telephone.setTelePhoneNumber("52652627");
		return telephone;
	}

	public static Telephone getTelephone_Three() {
		Telephone telephone = new Telephone();
		telephone.setStatusCode("ACTV");
		telephone.setCountryCode("+1");
		Customer customer = new Customer();
		customer.setCustomerId(1234);
		customer.setName("three");
		telephone.setCustomer(customer);
		telephone.setTelePhoneNumber("64376982982");
		return telephone;
	}

	public static List<Telephone> getPhoneList() {
		List<Telephone> phoneList = new ArrayList<>();
		phoneList.add(getTelephone_One());
		phoneList.add(getTelephone_Two());
		phoneList.add(getTelephone_Three());
		return phoneList;
	}

}
