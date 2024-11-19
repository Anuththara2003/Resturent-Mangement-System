package com.assignment.resturentmanagementsystem.Login;

import com.assignment.resturentmanagementsystem.Crud.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public boolean authenticate(String enteredUsername, String enteredPassword) throws SQLException, ClassNotFoundException {
        try{
            ResultSet result = CrudUtil.execute("SELECT * FROM user WHERE User_Name = ? AND password = ?", enteredUsername, enteredPassword);


            if (result.next()) {
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean authenticateUsername(String enteredUsername) throws SQLException, ClassNotFoundException {
        try{
            ResultSet result = CrudUtil.execute("SELECT * FROM user WHERE User_Name = ?", enteredUsername);


            if (result.next()) {
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
