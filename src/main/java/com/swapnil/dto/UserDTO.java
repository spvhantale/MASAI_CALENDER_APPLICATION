package com.swapnil.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@Email(message="correct the mail")
	private String mail;
//	@Pattern(regexp = "^[a-zA-Z]$", message = "No Special Characters Allowed.")
	private String firstName;
//	@Pattern(regexp = "^[a-zA-Z]$", message = "No Special Characters Allowed.")
	private String lastName;
	@Size(min = 10,max = 10 ,message = "mobile number should be 10 digit")
	private String mobileNumber;
//	@Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])).{6,12}$",message="not allowed")
	private String password;
	@Past(message="date should be past")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
}
