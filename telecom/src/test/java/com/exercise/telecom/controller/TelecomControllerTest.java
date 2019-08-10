package com.exercise.telecom.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.exercise.telecom.TelecomApplication;
import com.exercise.telecom.config.TelecomServiceConfig;
import com.exercise.telecom.repository.CustomerRepository;
import com.exercise.telecom.repository.TelePhoneRepository;
import com.exercise.telecom.utility.TelephoneDataUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The junit test class for telecom controller
 * 
 * @author jisha
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = TelecomApplication.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TelecomServiceConfig.class })
@TestPropertySource(value = { "classpath:telecom-persistece-config-test.properties",
		"classpath:telecom-application-test.properties" })
@FixMethodOrder(MethodSorters.JVM)
public class TelecomControllerTest {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TelecomControllerTest.class);

	private MockMvc mvc;

	@MockBean
	TelePhoneRepository telePhoneRepository;

	@MockBean
	CustomerRepository customerRepository;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(customerRepository);
		Mockito.reset(telePhoneRepository);
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@DisplayName("Test service instances")
	public void testControllerInstance() {
		assertNotNull(customerRepository);
		assertNotNull(telePhoneRepository);
	}

	@Test
	@DisplayName("Test getAllPhoneNumbers() for SUCCESS response")
	public void testGetAllPhoneNumbers_SUCCESS() throws Exception {

		when(telePhoneRepository.findAll()).thenReturn(TelephoneDataUtility.getPhoneList());

		ResultActions actions = mvc
				.perform(get("/telecom//getAllPhoneNumbers").accept(APPLICATION_JSON).contentType(APPLICATION_JSON));

		MvcResult result = actions.andReturn();
		LOGGER.info(result.getResponse().getContentAsString());

		actions.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
				.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[0].countryCode", is("+44")))
				.andExpect(jsonPath("$[0].phoneNumber", is("99999")));
	}

	@Test
	@DisplayName("Test getAllPhoneNumbersByCustomerId() for SUCCESS")
	public void testGetAllPhoneNumbersByCustomerId_SUCCESS() throws Exception {

		when(telePhoneRepository.findByCustomerCustomerId(ArgumentMatchers.any(Integer.class)))
				.thenReturn(TelephoneDataUtility.getPhoneList());

		ResultActions actions = mvc.perform(get("/telecom/1234/getAllPhoneNumbersForCustomer").accept(APPLICATION_JSON)
				.contentType(APPLICATION_JSON));

		MvcResult result = actions.andReturn();
		LOGGER.info(result.getResponse().getContentAsString());

		actions.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
				.andExpect(jsonPath("$").isNotEmpty()).andExpect(jsonPath("$[1].countryCode", is("+91")))
				.andExpect(jsonPath("$[1].phoneNumber", is("52652627")));

	}

	@Test
	@DisplayName("activatePhone for a customer ")
	public void testActivatePhone() throws Exception {

		when(customerRepository.existsById(ArgumentMatchers.any(Integer.class))).thenReturn(true);

		when(telePhoneRepository.findTelephoneByTelePhoneNumberAndCustomerCustomerId(ArgumentMatchers.any(String.class),
				ArgumentMatchers.any(Integer.class))).thenReturn(TelephoneDataUtility.getTelephone_One());

		when(telePhoneRepository.save(ArgumentMatchers.any())).thenReturn(TelephoneDataUtility.getTelephone_Modifiy());

		ResultActions actions = mvc.perform(
				post("/telecom/1234/activatePhone").content(mapToJson(TelephoneDataUtility.getTelephoneDto_Update()))
						.accept(APPLICATION_JSON).contentType(APPLICATION_JSON));

		MvcResult result = actions.andReturn();

		actions.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
				.andExpect(jsonPath("$.code", is("-1")))
				.andExpect(jsonPath("$.message", is("The phone is successfully activated for the customer")));
	}

	/**
	 * Convert object to String
	 * 
	 * @param obj
	 * @return String
	 * @throws JsonProcessingException
	 */
	private String mapToJson(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
