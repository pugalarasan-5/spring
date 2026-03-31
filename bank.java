package com.example.digital_payment_management.entity;
import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class bank
{	@NotBlank
	@Size(min = 2, max = 50)
	@Pattern(regexp = "^[a-zA-Z ]+$")
	private String name;

	@NotBlank
	private String address;

	@Id
	@NotBlank
	@Pattern(regexp = "^[0-9]{9,18}$")
	private String accountNo;
	@Email
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^(ACTIVE|INACTIVE)$")
	private String status;
	@NotNull
	@DecimalMin(value = "0.0", inclusive = true)
	private BigDecimal balance;
	@NotBlank
	@Pattern(regexp = "^[6-9][0-9]{9}$")
	private String phoneNumber;
	@OneToMany(mappedBy = "bank")
    private List<user> users;
}
