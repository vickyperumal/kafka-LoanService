package com.kafka.applyloan.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kafka.applyloan.entity.LoanDetailsEntity;
import com.kafka.applyloan.repo.LoanRepo;
import com.kafka.common.model.LoanDetails;
import com.kafka.common.model.ProcessLoanDetails;

import lombok.extern.slf4j.Slf4j;

@Service
public class LoanKafkaConsumerService {

	@Autowired
	private LoanRepo loanRepo;

	@Autowired
	private ModelMapper modelMapper;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@KafkaListener(id = "consumer-1", groupId = "group-id-2", topics = "loan-topic")
	public void receiveMessage(@Payload ProcessLoanDetails user, @Header(KafkaHeaders.OFFSET) long offset) {
		logger.info("2:Received [{}] with offset [{}]", user, offset);
		saveLoanDetails(user);

	}

	public void saveLoanDetails(ProcessLoanDetails loans) {
		try {
		if (!CollectionUtils.isEmpty(loans.getLoanDetails())) {
			List<LoanDetailsEntity> loanList = modelMapper.map(loans.getLoanDetails(),
					new TypeToken<List<LoanDetailsEntity>>() {
					}.getType());
			loanRepo.saveAll(loanList);
			logger.info("Saved loan details successfully");
		}
		}
		catch(Exception e) {
			logger.error("Error occured during saving loan details",e.getMessage());
		}
	}
}
