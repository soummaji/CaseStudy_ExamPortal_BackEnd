package com.examapp.examportal.entity;
import javax.persistence.*;
@Entity
//@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String email;
    private String password;
    private boolean examStatus;

    public Student() {
    }
    

	public Student(int id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
	
	

    public Student(int id, String fullName, String email, String password, boolean examStatus) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.examStatus = examStatus;
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

	public boolean isExamStatus() {
		return examStatus;
	}


	public void setExamStatus(boolean examStatus) {
		this.examStatus = examStatus;
	}


	@Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
