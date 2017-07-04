package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
