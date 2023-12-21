package com.querevalu.sisgestion.validator;

import com.querevalu.sisgestion.entity.Infraction;
import com.querevalu.sisgestion.exception.ValidateServiceException;

public class InfractionValidator {
	public static void save(Infraction infraction) {
		if(infraction.getDni() == null || infraction.getDni().isEmpty()) {
			throw new ValidateServiceException("DNI is required");
		}
		if(infraction.getDni().length() > 100) {
			throw new ValidateServiceException("The DNI must be a maximum of 8 characters");
		}
		if(infraction.getInfraction() == null) {
			throw new ValidateServiceException("Infraction is required");
		}
		if(infraction.getDate() == null) {
			throw new ValidateServiceException("Date is required");
		}
		if(infraction.getPlate() == null) {
			throw new ValidateServiceException("Plate is required");
		}
	}
}
