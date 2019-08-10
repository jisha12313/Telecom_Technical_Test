package com.exercise.telecom.util;

import com.exercise.telecom.dto.ResponseStatusDto;

public class Utility {
	public static ResponseStatusDto createResponseStatus(String code, String reasonCode, String message) {
		return new ResponseStatusDto(code, reasonCode, message);
	}
}
