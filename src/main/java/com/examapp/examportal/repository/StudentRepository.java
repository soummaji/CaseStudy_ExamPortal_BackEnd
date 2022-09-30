package com.examapp.examportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.examapp.examportal.entity.Student;

//@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student,Integer> {

    public Student findByEmail(String name);

    @Query("select password from Student s where email = ?1")
    public String findPasswordByName(String name);
    
    @Query("select id from Student s where email =?1")
    public String findIdByStudent(String name);
    
//    @Query("delete s.password from Student s where id =?1")
//    public boolean deleteStudent(int id);

    @Modifying
    @Query("update Student s set s.examStatus='true' where s.email=?1")
    public void updateExamStatus(String email);
    
    @Query("select examStatus from Student s where email = ?1")
    public boolean selectExamStatus(String email);
}
