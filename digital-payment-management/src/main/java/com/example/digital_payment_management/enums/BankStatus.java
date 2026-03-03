package com.example.digital_payment_management.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BankStatus {
    ACTIVE,
    INACTIVE,
    BLOCKED,
    SUSPENDED;
	
	@JsonCreator
    public static BankStatus from(String value) {
        return BankStatus.valueOf(value.toUpperCase());
    }
}
