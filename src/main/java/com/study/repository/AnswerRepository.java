package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
