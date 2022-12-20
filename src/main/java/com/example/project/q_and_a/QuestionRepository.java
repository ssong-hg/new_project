package com.example.project.q_and_a;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {//question 넘기고 주키는 integer타입이다
	List<Question> findByTitle(String title);
	Question findByTitleAndContent(String title, String content);
	Optional<Question> findByQuestionNo (Integer questionNo);
	
	Page<Question> findAll(Pageable pageable);
	
	
}
