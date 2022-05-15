package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class Employee {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroemployee?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertEmployee(String employeeNIC, String employeeName, String employeeType, String employeePhoneNo)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into employee1(`employeeID`,`employeeNIC`,`employeeName`,`employeeType`,`employeePhoneNo`)" + " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, employeeNIC);
			 preparedStmt.setString(3, employeeName);
			 preparedStmt.setString(4, employeeType);
			 preparedStmt.setString(5, employeePhoneNo);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newEmployee = readEmployee(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Employee.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	public String readEmployee()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>NIC</th><th>Employee Name</th><th>Employee Type</th><th>Phone Number</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from employee1";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String employeeID = Integer.toString(rs.getInt("employeeID"));
				 String employeeNIC = rs.getString("employeeNIC");
				 String employeeName = rs.getString("employeeName");
				 String employeeType = rs.getString("employeeType");
				 String employeePhoneNo = rs.getString("employeePhoneNo");
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidEmployeeIDUpdate\' name=\'hidEmployeeIDUpdate\' type=\'hidden\' value=\'" + employeeID + "'>" 
							+ employeeNIC + "</td>"; 
				output += "<td>" + employeeName + "</td>";
				output += "<td>" + employeeType + "</td>";
				output += "<td>" + employeePhoneNo + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-empid='" + employeeID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Employee.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	
	public String updateEmployee(String employeeID, String employeeNIC, String employeeName, String employeeType, String employeePhoneNo)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE employee1 SET employeeNIC=?,employeeName=?,employeeType=?,employeePhoneNo=?"  + "WHERE employeeID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, employeeNIC);
			 preparedStmt.setString(2, employeeName);
			 preparedStmt.setString(3, employeeType);
			 preparedStmt.setString(4, employeePhoneNo);
			 preparedStmt.setInt(5, Integer.parseInt(employeeID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newEmployee = readEmployee();    
			output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Employee.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deleteEmployee(String employeeID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from employee1 where employeeID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(employeeID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newEmployee = readEmployee();    
			output = "{\"status\":\"success\", \"data\": \"" +  newEmployee + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Employee.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
