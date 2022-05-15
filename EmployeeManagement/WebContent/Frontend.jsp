<%@ page import="com.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/employeevali.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Employee Management</h1>
				<form id="formEmployee" name="formEmployee" method="post" action="Frontend.jsp">  
					NIC:  
 	 				<input id="employeeNIC" name="employeeNIC" type="text"  class="form-control form-control-sm">
					<br>Employee Name:   
  					<input id="employeeName" name="employeeName" type="text" class="form-control form-control-sm">   
  					<br>Employee Type:   
  					<input id="employeeType" name="employeeType" type="text"  class="form-control form-control-sm">
					<br>Phone Number:   
  					<input id="employeePhoneNo" name="employeePhoneNo" type="text"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidEmployeeIDSave" name="hidEmployeeIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divEmployeeGrid">
					<%
						Employee EmployeeObj = new Employee();
						out.print(EmployeeObj.readEmployee());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>