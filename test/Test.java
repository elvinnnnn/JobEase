package test;

import java.sql.*;

public class Test {

    public static void main(String[] args) {
        String query = "select companyName from JobOpportunity where jobID=103";
        String url = "jdbc:sqlite:jobEaseDB.db";
        
        // Connect to the Database
        try {
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(query);
            res.next();
            String companyName = res.getString(1);
            System.out.println(companyName);
            con.close();

        } 
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        
    }
}