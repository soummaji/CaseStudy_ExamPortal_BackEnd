package com.examapp.examportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examapp.examportal.entity.StudentAnswers;

public interface StudentAnswersRepository extends JpaRepository<StudentAnswers,Integer> {
	
	@Query("select s from StudentAnswers s where student_id = ?1")
	List<StudentAnswers> findAnswersById(int id);
	
	@Query("select s.answers from StudentAnswers s where student_id=?1 and subject=?2")
	List<String> displayAnswersByStudentIdAndSubject(int student_id,String subject);

	@Query("select distinct s.subject from StudentAnswers s where student_id=?1")
	public List<String> checkSubject(int id , String subject);
}
