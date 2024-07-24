
package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAO {
    public boolean validate(String accountNumber, String password) {
        boolean status = false;
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Customer WHERE accountNumber=? AND password=?");
            ps.setString(1, accountNumber);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}