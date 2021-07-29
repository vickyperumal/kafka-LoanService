package com.kafka.applyloan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Loan_Details")
public class LoanDetailsEntity {

	private String loanType;
	private Long loanAmount;

	private String loanDate;
	private Double rateOfInterest;
	private Integer loanDuration;
	@Id
	private Long customerId;
	private Long monthlySalary;
	private Integer creditScore;
}
