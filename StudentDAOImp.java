package com.cognixia.jump.jdbc.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.jdbc.connection.ConnectionManagerProperties;

public class StudentDAOImp implements StudentDAO {
	private Connection conn = ConnectionManagerProperties.getConnection();
	@Override
	public List<Student> getAllStudents() {
		List<Student> stuList = new ArrayList<>();
		
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from student"); ) {
			
			while(rs.next()) {
				
				int studentId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String gender = rs.getString(4);
				String dateOfBirth = rs.getString(5);
				int credits = rs.getInt(6);
				
				Student stu = new Student (studentId, firstName, lastName, gender, dateOfBirth, credits);
				stuList.add(stu);
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return stuList;
	}

	@Override
	public Student getStudentById(int id) {
		Student stu = null;
		try(PreparedStatement pstmt = conn.prepareStatement("select * from student where student_id = ?")) {
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int studentId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String gender = rs.getString(4);
				String dateOfBirth = rs.getString(5);
				int credits = rs.getInt(6);

				
				stu = new Student (studentId, firstName, lastName, gender, dateOfBirth, credits);
				
			}
			
			
		} catch(SQLException e) {
			
		}
		
		
		return stu;
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

			pstmt.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}
	
	@Override
	public boolean updateStudent(Student student) {
		try(PreparedStatement pstmt = conn.prepareStatement("update student set first_name = ?, last_name = ?, gender = ?, date_of_birth = ?, credits = ?, where student_id = ?");) {
			
			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getGender());
			pstmt.setString(4, student.getDob());
			pstmt.setInt(5, student.getCredits());
			
			pstmt.setInt(6, student.getId());
			
			int update = pstmt.executeUpdate();
			
			if(update > 0) {
				return true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
