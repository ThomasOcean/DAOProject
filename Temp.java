package com.cognixia.jump.jdbc.project;

import java.sql.Date;

import com.cognixia.jump.jdbc.dao.Department;
import com.cognixia.jump.jdbc.dao.DepartmentDAO;
import com.cognixia.jump.jdbc.dao.DepartmentDAOImp;



public class Temp {
	
	public static void main(String[] args) {
		
		
		// get in info for student
		String fName = "Orquidia";
		String lName = "Moreno";
		String gender = "F";
		Date dob = new Date(2000-12-12);
		int credits = 30;
		// gender, dob, credits....
		
		// ask student which department they're in
		DepartmentDAO deptDao = new DepartmentDAOImp();
		
		for(Department dept : deptDao.getAllDepartments()) {
			System.out.println(dept);
		}
		
		Department dept = deptDao.getDepartmentByID(10000);
		
		
		int id = 0;
		String street = "this street";
		String city = "a city";
		String state = "NY";
		String zip = "12345";
		
		Address address = new Address(id, street, city, state, zip);
		
		
		
		
		//Student student = new Student(id, fName, lName, gender, dob, credits, address, dept);
		
		//Display all students
		StudentDAO stuDAO = new StudentDAOImp();
		
		System.out.println("Get all departments");
		
		for (Student stu : stuDAO.getAllStudents() ) {
			
			System.out.println(stu);
		}
				
	
		//Add student
		Student newStudent = new Student(id, fName, lName, zip, dob, credits, address, dept);
		
		boolean added =  stuDAO.addStudent(newStudent);
		
		if(added) {
			System.out.println("Added using Department");
		}
		
		
		// 1. Setting up the Address DAO, 1-2 people
		// 2. Split up Student DAO amongst 2-3 people to do methods
		// 3. Work on menu, 2 people
		
		
		
		
		
	}

}