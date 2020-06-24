package com.cognixia.jump.jdbc.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentDAOImp implements StudentDAO {

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addStudent(Student student) {
		Connection conn = ConnectionManagerProperties.getConnection();
		// insert into student values(id, fname, lname, gender, dob, credits, addr_id, dept_id);
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into student values(id = ?, fname = ?, lname = ?, gender = ?, " +
					"dob = ?, credits = ?, addr_id = ?, dept_id = ?)");

			// grab the id for the department
			int deptId = student.getDept().getId();

			// grab the id for the address
			int addrId = student.getAddress().getId();

			// grab the first name for student
			String fname = student.getFirstName();

			// grab the last name for student
			String lname = student.getLastName();

			// grab the gender for student
			String gender = student.getGender();

			// grab the dob for student
			int dob = student.getDob();

			// grab the credits for a student
			int credits = student.getCredits();

			// add in your values to statement
			pstmt.setInt(1, student.getId())
			pstmt.setInt(2, student.getDept().getId());
			pstmt.setString(3, student.getFirstName());
			pstmt.setString(4, student.getLastName());
			pstmt.setString(5, student.getGender());
			pstmt.setInt(6, student.getDob());
			pstmt.setInt(7, student.getCredits());
			pstmt.setInt(8, student.getAddress().getId());

			int insert = pstmt.executeUpdate();

			if (insert > 0) {
				return true
			}

			pstsmt.close()

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
