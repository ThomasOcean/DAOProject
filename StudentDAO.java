package com.cognixia.jump.jdbc.project;

import java.util.List;

public interface StudentDAO {

	public List<Student> getAllStudents();
	
	public Student getStudentById(int id);
	
	public boolean addStudent(Student student);
	
	public boolean updateStudent(Student student);
	
	public boolean deleteStudent(int id);
	
}
