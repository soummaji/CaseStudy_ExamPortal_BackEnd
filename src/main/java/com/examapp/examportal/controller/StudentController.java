package com.examapp.examportal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examapp.examportal.encrypter.PasswordDecrypter;
import com.examapp.examportal.encrypter.PasswordEncrypter;
import com.examapp.examportal.entity.Student;
import com.examapp.examportal.entity.StudentAnswers;
import com.examapp.examportal.service.StudentService;

@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    StudentService service;

//    Student registration-----------------
    @PostMapping("/studentRegistration")
    public boolean studentRegistration(@RequestBody Student s) {
        Student stuobj = null;
        String tempName =  s.getEmail();
        if(tempName != null && !"".equals(tempName)){
            stuobj = service.fetchStudentByUserName(tempName);
            if(stuobj != null){
                return false;
            }
        }
        else {
            return false;
        }
        PasswordEncrypter pe = new PasswordEncrypter();
        s.setPassword(pe.encrypter(s.getPassword()));
        service.createStudent(s);
        return true;
    }

//  Student Login-----------------
    @PostMapping("/studentLogin")
    public Map<String, String> studentLogin(@RequestBody Student s){
    	 HashMap<String, String> map = new HashMap<>();
        String tempName = s.getEmail();
        String tempPass = s.getPassword();
        String enPass = service.fetchPasswordByName(tempName);
        String stId = service.fetchIdByName(tempName);
        PasswordDecrypter pd = new PasswordDecrypter();
        boolean check = pd.decrypter(tempPass, enPass);
        if(check == false){
        	map.put("State", "False");
            return map;
        }
        service.updateExamStatus(tempName);
        map.put("State", "True");
        map.put("StudentId", stId);
        return map;
        
    }

//    Student Add Answers----------------------
    @PostMapping("/addAnswer/{student_id}/{student_subject}")
    public boolean addAnswer(@PathVariable int student_id ,@PathVariable String student_subject ,  @RequestBody StudentAnswers studentAnswers){
        System.out.println(student_id);
        System.out.println(student_subject);
        System.out.print(studentAnswers.getAnswers());
        service.addAnswers(student_id,student_subject,studentAnswers.getAnswers());
        return true;
    }

//    Display Answers---------------------------------
    @GetMapping("/displayAnswersByStudent/{student_id}/{subject}")
    public List<String> displayAnswersByStudent(@PathVariable int student_id,@PathVariable String subject) {
    	//int i=Integer.parseInt(student_id);
    	return service.fetchAnswersByIdAndSub(student_id, subject);
    }

    // @PutMapping("/deleteStudentData/{student_id}/{email}")
    // public boolean deleteByStudentId(@PathVariable int student_id,@PathVariable String email){
    //     service.deleteStudent(student_id);
    //     Student s = new Student();
    //     s.setEmail(email);
    //     s.setPassword("");
    //     service.createStudent(s);
    //     return true;
    // }

    @GetMapping("/getExamStatus/{student_email}")
    public boolean getExamStatus(@PathVariable String student_email) {
    	 return service.fetchExamStatus(student_email);
    }

    @GetMapping("/getExamSubject/{student_id}/{subject}")
    public boolean getExamSubject(@PathVariable int student_id,@PathVariable String subject){
        return service.fetchExamSubject(student_id,subject);
    }
}
