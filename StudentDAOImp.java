package com.cognixia.jump.jdbc.project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import com.cognixia.jump.jdbc.project.*;
import com.cognixia.jump.jdbc.connection.ConnectionManagerProperties;
import com.cognixia.jump.jdbc.dao.Department;
import com.cognixia.jump.jdbc.dao.DepartmentDAO;
import com.cognixia.jump.jdbc.dao.DepartmentDAOImp;
//import com.cognixia.jump.jdbc.dao.DepartmentDAOImp;
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
				Date dateOfBirth = rs.getDate(5);
				int credits = rs.getInt(6);
				int addrID = rs.getInt(7);
				int deptID = rs.getInt(8);
				AddressDAO addrDao =  new AddressDAOImp();
				Address addr =addrDao.getAddressById(addrID) ;
				DepartmentDAO deptDao = new DepartmentDAOImp();
				Department dept = deptDao.getDepartmentByID(deptID);
				Student stu = new Student (studentId, firstName, lastName, gender, dateOfBirth, credits,addr,dept);
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
				Date dateOfBirth = rs.getDate(5);
				int credits = rs.getInt(6);
				int addrID = rs.getInt(7);
				int deptID = rs.getInt(8);
				AddressDAO addrDao =  new AddressDAOImp();
				Address addr =addrDao.getAddressById(addrID) ;
				DepartmentDAO deptDao = new DepartmentDAOImp();
				Department dept = deptDao.getDepartmentByID(deptID);


				stu = new Student (studentId, firstName, lastName, gender, dateOfBirth, credits,addr,dept);

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
			PreparedStatement pstmt = conn.prepareStatement("insert into student (student_id, "
					+ "first_name, last_name, gender, " +
					"date_of_birth, credits, address_id, dept_id)"
					+ "Values(?,?,?,?,?,?,?,?)");

			// add in your values to statement
			pstmt.setInt(1, student.getId());
			pstmt.setString(2, student.getFirstName());
			pstmt.setString(3, student.getLastName());
			pstmt.setString(4, student.getGender());
			pstmt.setDate(5, student.getDob());
			pstmt.setInt(6, student.getCredits());
			pstmt.setInt(7, student.getAddress().getId());
			pstmt.setInt(8, student.getDept().getId());
			AddressDAO addrDAO= new AddressDAOImp();
			addrDAO.addAddress(student.getAddress());
			int insert = pstmt.executeUpdate();

			if (insert > 0) {
				return true;
			}

			pstmt.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		try(PreparedStatement pstmt = conn.prepareStatement("update student set first_name = ?, "
				+ "last_name = ?, gender = ?, date_of_birth = ?, credits = ? where student_id = ?");) {
			
			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getGender());
			pstmt.setDate(4, student.getDob());
			pstmt.setInt(5, student.getCredits());

			
			pstmt.setInt(6, student.getId());
			AddressDAO addrDAO= new AddressDAOImp();
			addrDAO.updateAddress(student.getAddress());
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
		try(PreparedStatement pstmt = conn.prepareStatement("delete from student where student_id = ?")) {
			
			pstmt.setInt(1, id);
			
			int deleted = pstmt.executeUpdate();
			
			if(deleted > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}