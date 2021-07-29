package com.kafka.applyloan.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kafka.applyloan.entity.LoanDetailsEntity;


@Repository
public interface LoanRepo extends MongoRepository<LoanDetailsEntity, Long> {

}
