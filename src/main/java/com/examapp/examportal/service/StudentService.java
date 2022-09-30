package com.examapp.examportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.examapp.examportal.entity.Student;
import com.examapp.examportal.entity.StudentAnswers;
import com.examapp.examportal.repository.StudentAnswersRepository;
import com.examapp.examportal.repository.StudentRepository;
import com.examapp.examportal.repository.SubjectRepository;

@org.springframework.stereotype.Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentAnswersRepository studentAnswersRepository;
    
    @Autowired
    SubjectRepository subjectRepository;

    public void createStudent(Student student){
        studentRepository.save(student);
    }

    public Student fetchStudentByUserName(String name) {
        return studentRepository.findByEmail(name);
    }
    public String fetchPasswordByName(String name){
        return studentRepository.findPasswordByName(name);
    }
    
    public String fetchIdByName(String name) {
    	return studentRepository.findIdByStudent(name);
    }

    public boolean addAnswers(int student_id , String student_subject , String answer){
    	StudentAnswers s = new StudentAnswers(answer,student_subject,student_id);
        studentAnswersRepository.save(s);
        return true;
    }
    
    public List<String> fetchAnswersByIdAndSub(int id, String subject){
    	return studentAnswersRepository.displayAnswersByStudentIdAndSubject(id, subject);
    }
    
    public boolean deleteStudent(int id){
        studentRepository.deleteById(id);
        return true;
    }

    //@Transactional
    public void updateExamStatus(String email) {
    	studentRepository.updateExamStatus(email);
    }
    
    public boolean fetchExamStatus(String email) {
    	return studentRepository.selectExamStatus(email);
    }

    public boolean fetchExamSubject(int id,String subject){
        List<String> temp = studentAnswersRepository.checkSubject(id,subject);
        for(String s : temp) {
        System.out.println(s);
        }
        for(int i = 0; i<temp.size(); i++) {
        	if(temp.get(i).equalsIgnoreCase(subject)) {
        		return false;
        	}
        }
//        if(s.equalsIgnoreCase(subject)){
//            return "false";
//        }
        return true;
    }
    
}
