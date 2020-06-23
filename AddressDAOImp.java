package com.cognixia.jump.jdbc.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.jdbc.connection.ConnectionManagerProperties;

public abstract class AddressDAOImp implements AddressDAO {

	@Override
	public Address getAddressById(int id) {
		
		Connection conn = ConnectionManagerProperties.getConnection();
		// TODO Auto-generated method stub
		Address address = null;
		
		// select * from department where dept_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement("select * from address where address_id = ?")) {
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int addressId = rs.getInt(1);
				String street = rs.getString(2);
				String city = rs.getString(3);
				String state = rs.getString(4);
				String zip = rs.getString(5);
				
				address = new Address(addressId, street, city, state, zip);
				
			}
			
			
		} catch(SQLException e) {
			
		}
		
		return address;
	}

	@Override
	public boolean updateAddress(Address address) {
		
		Connection conn = ConnectionManagerProperties.getConnection();
		
		try(PreparedStatement pstmt = conn.prepareStatement("update address set street = ?, city = ?, state = ?, zip_code = ? where address_id = ?");) {
			
			pstmt.setString(1, address.getStreet());
			pstmt.setString(2, address.getCity());
			pstmt.setString(3, address.getState());
			pstmt.setString(4, address.getZip());
			
			pstmt.setInt(5, address.getId());
			
			int update = pstmt.executeUpdate();
			
			if(update > 0) {
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean addAddress(Address address) {
		// TODO Auto-generated method stub
		return false;
	}


}
