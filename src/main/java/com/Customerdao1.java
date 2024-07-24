package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customerdao1 {
    // Other methods...

    public boolean changePassword(String accountNumber, String oldPassword, String newPassword) {
        boolean status = false;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Customer WHERE accountNumber=? AND password=?");
            ps.setString(1, accountNumber);
            ps.setString(2, oldPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps = con.prepareStatement("UPDATE Customer SET password=? WHERE accountNumber=?");
                ps.setString(1, newPassword);
                ps.setString(2, accountNumber);
                ps.executeUpdate();
                status = true;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

	public boolean deposit(String accountNumber, double amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
